package me.oudedong.springbootDeveloper.service;

import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.config.jwt.TokenProvider;
import me.oudedong.springbootDeveloper.domain.RefreshToken;
import me.oudedong.springbootDeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new IllegalArgumentException("Unexpected refresh token"));
    }
}
