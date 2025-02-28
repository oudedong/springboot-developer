package me.oudedong.springbootDeveloper.dto;

import lombok.Getter;
import me.oudedong.springbootDeveloper.domain.Article;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        title = article.getTitle();
        content = article.getContent();
    }
}
