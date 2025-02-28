package me.oudedong.springbootDeveloper.service;

import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.config.jwt.TokenProvider;
import me.oudedong.springbootDeveloper.domain.RefreshToken;
import me.oudedong.springbootDeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewToken(String refreshToken){
        if(!tokenProvider.vaildToken(refreshToken))
            throw new IllegalArgumentException("Unexpected Token...");

        Long id = refreshTokenService.findByRefreshToken(refreshToken).getId();
        User user = userService.findById(id);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
