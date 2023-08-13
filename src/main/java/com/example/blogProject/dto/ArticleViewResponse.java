package com.example.blogProject.dto;

import com.example.blogProject.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article artcle){
        this.id = artcle.getId();
        this.title = artcle.getTitle();
        this.content = artcle.getContent();
        this.createdAt = artcle.getCreateAt();
    }
}
