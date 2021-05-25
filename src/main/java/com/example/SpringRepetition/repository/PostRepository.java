package com.example.SpringRepetition.repository;

import com.example.SpringRepetition.model.Category;
import com.example.SpringRepetition.model.Post;
import com.example.SpringRepetition.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByCategory(Category category, Sort sort);

    List<Post> findAllByCategoryAndAuthor(Category category, User author, Sort sort);

    List<Post> findAllByTitleInOrContentIn(List<String> titleWords, List<String> contentWords);

    @Query(
            value = "SELECT p.category, count(*) FROM Post p GROUP BY p.category ORDER BY 2 DESC",
            nativeQuery = true
    )
    Map<Object, Object> postStatistics();
}
