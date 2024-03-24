package com.example.team16project.article;

import com.example.team16project.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;
    String title;
    String contents;
    Timestamp createdAt;
    Integer likeCount;
    Integer viewCount;
    Timestamp updatedAt;
}
