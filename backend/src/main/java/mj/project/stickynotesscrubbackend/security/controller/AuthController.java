package mj.project.stickynotesscrubbackend.security.controller;

import com.sun.net.httpserver.Authenticator;
import mj.project.stickynotesscrubbackend.app_user.dto.AppUserDto;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.app_user.service.AppUserService;
import mj.project.stickynotesscrubbackend.security.jwt.JwtResponse;
import mj.project.stickynotesscrubbackend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> loginAppUser(@RequestBody AppUserDto appUserDto) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(appUserDto.getUsername(), appUserDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        JwtResponse jwtResponse = JwtResponse.builder()
                .token(jwt)
                .id(String.valueOf(appUser.getId()))
                .username(appUser.getUsername())
                .build();
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerAppUser(@RequestBody AppUserDto appUserDto) {
        String username = appUserDto.getUsername();
        if (appUserService.existsByUsername(username)) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        } else {
            appUserService.save(AppUser.builder()
                    .username(appUserDto.getUsername())
                    .password(encoder.encode(appUserDto.getPassword()))
                    .build());
            return new ResponseEntity<Authenticator.Success>(HttpStatus.CREATED);
        }
    }
}