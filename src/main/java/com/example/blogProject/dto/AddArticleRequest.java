package com.example.blogProject.dto;

import com.example.blogProject.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){ // 빌터패턴을 사용해 DTO를 엔티티로 만들어주는 메서드이다.
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
