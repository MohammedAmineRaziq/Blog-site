package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByPublicId(String postPublicId);

    @Query("SELECT p FROM Post p WHERE p.titre LIKE %?1%"
            + " OR p.contenu LIKE %?1%" +" OR p.category LIKE %?1%"
    )
    public List<Post> search(String keyword);

}
