package mj.project.stickynotesscrubbackend.security.jwt;

import io.jsonwebtoken.*;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    // todo: change this!!!
    private final String jwtSecret = "server_secret";
    private final int jwtExpirationMs = 1;

    public String generateJwtToken(Authentication authentication) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((appUser.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String jwtToken) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody().getSubject();
    }

    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ignored) {
        }
        return false;
    }
}