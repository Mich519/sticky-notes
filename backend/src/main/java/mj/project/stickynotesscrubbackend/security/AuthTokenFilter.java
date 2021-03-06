package mj.project.stickynotesscrubbackend.security;

import mj.project.stickynotesscrubbackend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService appUserService;

    @Autowired
    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsService appUserService) {
        this.jwtUtils = jwtUtils;
        this.appUserService = appUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            //String jwt = parseJwt(request);
            Optional<Cookie> jwtCookie = Optional.empty();
            if(request.getCookies() != null ) {
                jwtCookie = Arrays.stream(request.getCookies())
                        .filter(cookie -> cookie.getName().equals("jwt"))
                        .findFirst();
            }

            if (jwtCookie.isPresent() && jwtUtils.validateJwtToken(jwtCookie.get().getValue())) {
                String username = jwtUtils.getUserNameFromJwtToken(jwtCookie.get().getValue());
                UserDetails userDetails = appUserService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}