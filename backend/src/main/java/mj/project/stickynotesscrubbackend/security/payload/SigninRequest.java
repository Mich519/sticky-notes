package mj.project.stickynotesscrubbackend.security.payload;

import lombok.Getter;

@Getter
public class SigninRequest {
    private String username;
    private String password;
}
