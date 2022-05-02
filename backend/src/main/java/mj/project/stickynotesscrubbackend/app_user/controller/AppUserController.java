package mj.project.stickynotesscrubbackend.app_user.controller;

import mj.project.stickynotesscrubbackend.app_user.dto.AppUserDto;
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
    public ResponseEntity<AppUserDto> findAppUser(@PathVariable int id) {
        Optional<AppUser> appUser = appUserService.findById(id);
        if (appUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AppUserDto appUserDto = AppUserDto.createFrom(appUser.get());
            return ResponseEntity.ok(appUserDto);
        }
    }

    @GetMapping
    public ResponseEntity<List<AppUserDto>> findAllUsers() {
        List<AppUserDto> appUserDtoList = appUserService.findAll().stream()
                .map(AppUserDto::createFrom)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appUserDtoList);
    }


    @PostMapping
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUserDto appUserDto) {
        appUserService.save(appUserDto.toEntity());
        return ResponseEntity.ok(appUserDto);
    }
}
