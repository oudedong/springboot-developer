package me.oudedong.springbootDeveloper.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.config.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

//        for(Cookie i : request.getCookies()){
//            System.out.println("쿠키명: " + i.getName());
//        }
//        for(Enumeration<String> i = request.getHeaderNames(); i.hasMoreElements();){
//            System.out.println("헤더명: " + i.nextElement());
//        }

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getToken(authorizationHeader);
        System.out.println("헤더: "+ authorizationHeader);
        System.out.println("토큰: "+ token);
        if(tokenProvider.vaildToken(token)){
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("인증된 이름: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        filterChain.doFilter(request, response);
    }
    String getToken(String authorizationHeader){
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer"))
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        return null;
    }
}
