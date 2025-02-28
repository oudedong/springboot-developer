package me.oudedong.springbootDeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oudedong.springbootDeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
