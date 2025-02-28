package me.oudedong.springbootDeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.dto.CreateAccessTokenRequest;
import me.oudedong.springbootDeveloper.dto.CreateAccessTokenResponse;
import me.oudedong.springbootDeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createAccessTokenRequest(@RequestBody CreateAccessTokenRequest request){

        String newAccessToken = tokenService.createNewToken(request.getRefreshToken());

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
}
