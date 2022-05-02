package mj.project.stickynotesscrubbackend.security.jwt;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtResponse {
    private String token;
    private String type;
    private String id;
    private String username;
}
