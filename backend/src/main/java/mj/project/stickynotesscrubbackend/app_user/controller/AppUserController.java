package mj.project.stickynotesscrubbackend.app_user.controller;

import mj.project.stickynotesscrubbackend.app_user.dto.SigninRequest;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.app_user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<SigninRequest> findAppUser(@PathVariable int id) {
        Optional<AppUser> appUser = appUserService.findById(id);
        if (appUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            SigninRequest appUserDto = SigninRequest.createFrom(appUser.get());
            return ResponseEntity.ok(appUserDto);
        }
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
