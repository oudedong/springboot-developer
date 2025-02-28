package me.oudedong.springbootDeveloper.service;

import lombok.*;
import me.oudedong.springbootDeveloper.domain.Article;
import me.oudedong.springbootDeveloper.dto.AddArticleRequest;
import me.oudedong.springbootDeveloper.dto.UpdateArticleRequest;
import me.oudedong.springbootDeveloper.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;//생성자가 하나라 자동주입해줌!!!!

    public Article save(AddArticleRequest request, String userName){
        return blogRepository.save(request.toEntity(userName));
    }
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
    public Article findById(Long id){
        return blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }
    public void delete(Long id){

        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository
                    .findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    private static void authorizeArticleAuthor(Article article){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("name:" + username);

        if(!username.equals(article.getAuthor()))
            throw new IllegalArgumentException("not Owner");
    }
}
