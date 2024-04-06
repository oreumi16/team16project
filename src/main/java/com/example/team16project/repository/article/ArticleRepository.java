package com.example.team16project.repository.article;

import com.example.team16project.domain.article.Article;
import com.example.team16project.dto.article.response.ArticleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select ceil(count(*) / :pagesize) as totalPages from article", nativeQuery = true)
    public int selectTotalPages(@Param("pagesize") int pageSize);

    @Query(value = "select article_id, user_id, title, contents, created_at, like_count, view_count, updated_at " +
            "from article order by article_id desc limit :pagesize offset :offset", nativeQuery = true)
    public List<Article> selectAllCoteBaords(@Param("pagesize") int pageSize, @Param("offset") int offset);

    Optional<Article> findByTitle(String title);

    Optional<Article> findByArticleId(Long articleId);

    @Query(value = "select ceil(count(*) / :pagesize) as totalPages from article where contents regexp :query", nativeQuery = true)
    public int searchPages(@Param("pagesize") int pageSize, @Param("query") String query);

    @Query(value = "select article_id, user_id, title, contents, created_at, like_count, view_count, updated_at " +
            "from article where contents regexp :query or title regexp :query order by article_id desc limit :pagesize offset :offset", nativeQuery = true)
    public List<Article> searchBoards(@Param("pagesize") int pageSize, @Param("offset") int offset, @Param("query") String query);

}
