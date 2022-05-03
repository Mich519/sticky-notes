package mj.project.stickynotesscrubbackend.security.payload.request;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String username;
    private String password;
}
