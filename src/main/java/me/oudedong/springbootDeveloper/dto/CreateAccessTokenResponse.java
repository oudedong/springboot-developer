package me.oudedong.springbootDeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateAccessTokenResponse {
    private String accessToken;
}
