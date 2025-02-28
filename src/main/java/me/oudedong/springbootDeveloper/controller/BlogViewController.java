package me.oudedong.springbootDeveloper.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.oudedong.springbootDeveloper.domain.RefreshToken;
import me.oudedong.springbootDeveloper.dto.ArticleListViewResponse;
import me.oudedong.springbootDeveloper.dto.ArticleViewResponse;
import me.oudedong.springbootDeveloper.repository.RefreshTokenRepository;
import me.oudedong.springbootDeveloper.repository.UserRepository;
import me.oudedong.springbootDeveloper.service.BlogService;
import me.oudedong.springbootDeveloper.service.RefreshTokenService;
import me.oudedong.springbootDeveloper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @GetMapping("/articles")
    public String getArticles(Model model, HttpServletRequest request){

        List<ArticleListViewResponse> articles;

        articles = blogService
                .findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        model.addAttribute("user", findUserName(request));

        return "articleList";
    }
    @GetMapping("/articles/{id}")
    public String getArticle(Model model, @PathVariable("id") Long id, HttpServletRequest request){
        model.addAttribute("article", new ArticleViewResponse(blogService.findById(id)));
        model.addAttribute("user", findUserName(request));
        return "article";
    }
    @GetMapping("/new-article")
    public String newArticle(Model model, @RequestParam(required = false) Long id){

        ArticleViewResponse articleViewResponse;

        if(id != null)
            model.addAttribute("article", new ArticleViewResponse(blogService.findById(id)));
        else
            model.addAttribute("article", new ArticleViewResponse());
        return "newArticle";
    }
    @GetMapping("/login")
    public String login(){
        //return "login";
        return "oauthLogin";
    }
    @GetMapping("/signup")
    public String signUp(){return "signup";}


    private String findUserName(HttpServletRequest request){

        String refreshToken = findRefreshToken(request.getCookies());
        String username;
        try {
            RefreshToken found = refreshTokenService.findByRefreshToken(refreshToken);
            username = userService.findById(found.getUserId()).getUsername();
        }
        catch(IllegalArgumentException e){
            return "방문자";
        }
        return username;
    }
    private String findRefreshToken(Cookie[] cookies){
        for(Cookie i : cookies)
            if(i.getName().equals("refresh_token"))
                return i.getValue();
        return "error";
    }
}
