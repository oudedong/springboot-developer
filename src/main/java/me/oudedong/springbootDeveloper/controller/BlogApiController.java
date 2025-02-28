package me.oudedong.springbootDeveloper.controller;

import lombok.*;
import me.oudedong.springbootDeveloper.domain.Article;
import me.oudedong.springbootDeveloper.dto.AddArticleRequest;
import me.oudedong.springbootDeveloper.dto.ArticleResponse;
import me.oudedong.springbootDeveloper.dto.UpdateArticleRequest;
import me.oudedong.springbootDeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;//생성자가 하나라 자동주입해줌!!!!

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal){//json타입을 객체로 매핑해줌
        Article savedArticle = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable("id") Long id){
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@PathVariable("id") Long id, @RequestBody UpdateArticleRequest request){
        Article article = blogService.update(id, request);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }
}
