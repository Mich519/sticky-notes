package mj.project.stickynotesscrubbackend.security.payload;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String username;
    private String password;
}
