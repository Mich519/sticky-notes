package mj.project.stickynotesscrubbackend.security.payload.request;

import lombok.Getter;

@Getter
public class SigninRequest {
    private String username;
    private String password;
}
