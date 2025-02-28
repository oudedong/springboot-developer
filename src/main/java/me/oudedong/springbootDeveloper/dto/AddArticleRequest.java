package me.oudedong.springbootDeveloper.dto;

import lombok.*;
import me.oudedong.springbootDeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    public String title;
    private String content;

    public Article toEntity(String author){
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
