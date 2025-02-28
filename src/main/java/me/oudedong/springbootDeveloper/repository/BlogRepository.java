package me.oudedong.springbootDeveloper.repository;

import me.oudedong.springbootDeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {}
