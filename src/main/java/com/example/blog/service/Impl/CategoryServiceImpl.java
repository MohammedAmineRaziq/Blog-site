package com.example.blog.service.Impl;

import com.example.blog.Util.Utils;
import com.example.blog.model.Category;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Utils utils;


    @Override
    public Category saveCategory(Category category) {
        category.setPublicId(utils.genereteRandomString(30));
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String categoryPublicId) {
        return categoryRepository.findByPublicId(categoryPublicId);
    }

    @Override
    public Category updateCategory(Category category) {
        Category category1=categoryRepository.findByPublicId(category.getPublicId());
        category1.setName(category.getName());
        category1.setDescription(category1.getDescription());
        return categoryRepository.save(category1);    }

    @Override
    public void deleteCategory(String categoryPublicId) {
        categoryRepository.deleteById(categoryRepository.findByPublicId(categoryPublicId).getId());
    }
}
