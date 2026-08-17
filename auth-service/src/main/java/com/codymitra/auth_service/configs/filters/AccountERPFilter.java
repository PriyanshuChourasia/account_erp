package com.codymitra.auth_service.configs.filters;

import com.codymitra.auth_service.configs.JwtConfig;
import com.codymitra.auth_service.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountERPFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("==".repeat(15));
        log.info("request 1 {}",request.getAuthType());
        log.info("request 2 {}",request.getDispatcherType());
        log.info("response 1 {}",response.getStatus());
        log.info("response 2 {}",response.getContentType());
        log.info("filter {}",filterChain);


        final String authHeader = request.getHeader("Authorization");

        log.info("Auth Header: {}",authHeader);

        if(authHeader == null || !authHeader.startsWith("Bearer ") ){
            log.warn("No Auth Header found");
            filterChain.doFilter(request,response);
            return;
        }


        final String token = authHeader.substring(7);
        final String username;

        try{
            username = jwtService.extractUsername(token);
            log.warn("No username extracted");
        }
        catch (Exception ex){
            switch (ex){
                case ExpiredJwtException expiredJwtException ->{
                    filterChain.doFilter(request,response);
                    return;
                }
                case MissingClaimException missingClaimException ->{
                    filterChain.doFilter(request,response);
                    return;
                }
                case MalformedJwtException malformedJwtException ->{
                    filterChain.doFilter(request,response);
                    return;
                }
                case UsernameNotFoundException usernameNotFoundException ->{
                    filterChain.doFilter(request,response);
                    return;
                }
                default -> {

                }
            }
            filterChain.doFilter(request,response);
            return;
        }



        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        }

        log.info("==".repeat(15));
    }
}
