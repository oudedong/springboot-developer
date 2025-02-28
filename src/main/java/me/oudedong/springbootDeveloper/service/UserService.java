package me.oudedong.springbootDeveloper.service;

import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.domain.User;
import me.oudedong.springbootDeveloper.dto.AddUserRequest;
import me.oudedong.springbootDeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest dto){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .build()
        ).getId();
    }
    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Unexpected user, ID:" + userId));
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("Unexpected user, email:" + email));
    }
}
