package mj.project.stickynotesscrubbackend.app_user.controller;

import mj.project.stickynotesscrubbackend.app_user.dto.SigninRequest;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.app_user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findAppUser(@PathVariable String username, Principal principal) {
        if (principal.getName().equals(username)) {
            Optional<AppUser> appUser = appUserService.findUserByUsername(username);
            return appUser
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping
    public ResponseEntity<List<SigninRequest>> findAllUsers() {
        List<SigninRequest> appUserDtoList = appUserService.findAll().stream()
                .map(SigninRequest::createFrom)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appUserDtoList);
    }

    @PostMapping
    public ResponseEntity<SigninRequest> createUser(@RequestBody SigninRequest appUserDto) {
        appUserService.save(appUserDto.toEntity());
        return ResponseEntity.ok(appUserDto);
    }
}
