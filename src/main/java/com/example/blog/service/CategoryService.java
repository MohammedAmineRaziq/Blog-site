package com.example.blog.service;

import com.example.blog.model.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategory(String categoryPublicId);

    Category updateCategory(Category category);

    void deleteCategory(String categoryPublicId);
}
