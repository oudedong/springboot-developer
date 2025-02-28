package me.oudedong.springbootDeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oudedong.springbootDeveloper.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
