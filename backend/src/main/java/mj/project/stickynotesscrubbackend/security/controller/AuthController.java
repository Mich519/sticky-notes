package mj.project.stickynotesscrubbackend.security.controller;

import com.sun.net.httpserver.Authenticator;
import mj.project.stickynotesscrubbackend.app_user.dto.SigninRequest;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.app_user.service.AppUserService;
import mj.project.stickynotesscrubbackend.security.jwt.JwtResponse;
import mj.project.stickynotesscrubbackend.security.jwt.JwtUtils;
import mj.project.stickynotesscrubbackend.security.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthController(AppUserService appUserService, JwtUtils jwtUtils, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
        this.appUserService = appUserService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<?> loginAppUser(@RequestBody SigninRequest signinRequest, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //AppUser appUser = (AppUser) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*60*60);
        cookie.setPath("/"); //todo: refresh token
        cookie.setSecure(false); // todo: change for prod
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerAppUser(@RequestBody SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        if (appUserService.existsByUsername(username)) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        } else {
            appUserService.save(AppUser.builder()
                    .username(signupRequest.getUsername())
                    .password(encoder.encode(signupRequest.getPassword()))
                    .build());
            return new ResponseEntity<Authenticator.Success>(HttpStatus.CREATED);
        }
    }
}
