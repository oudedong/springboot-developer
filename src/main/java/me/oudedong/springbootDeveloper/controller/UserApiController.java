package me.oudedong.springbootDeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.dto.AddUserRequest;
import me.oudedong.springbootDeveloper.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request);
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        //생성된 세션&컨텍스트를 삭제함!
        return "redirect:/login";
    }
    @GetMapping("/guest")
    public String guest(HttpServletRequest request){
        Authentication guestAuthentication = new UsernamePasswordAuthenticationToken(
                "guest",
                null,
                List.of(new SimpleGrantedAuthority("user"))
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(guestAuthentication);

        // 세션 생성 후 SecurityContext 저장
        HttpSession session = request.getSession(true); // 세션 강제 생성, 원래 로그인을 하지 않으면 세션이 형성되지 않음!!
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);//키:값을 나타냄, 여기서는 보안컨텍스트
        return "redirect:/articles";
    }
}
