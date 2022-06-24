package com.example.blog.repository;

import com.example.blog.model.Category;
import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByPublicId(String postPublicId);
    List<Category> findAll();

}