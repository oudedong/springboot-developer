package me.oudedong.springbootDeveloper.dto;

import lombok.*;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
}
