package com.example.blog.controller;

import com.example.blog.model.Category;
import com.example.blog.model.Post;
import com.example.blog.service.CategoryService;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoriesService;

    @GetMapping("/home")
    public String adminHome(Model model ,@Param("keyword") String keyword)
    {
        model.addAttribute("keyword", keyword);
        model.addAttribute("posts",this.postService.getAllPosts(keyword));
        return  "admin/home";
    }


    @GetMapping("/newPost")
    public String newPost(Model model)
    {
        model.addAttribute("post",new Post());
        model.addAttribute("categories",categoriesService.getAllCategories());
        return "admin/newPost";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute Post post , @RequestParam("img") MultipartFile image) throws IOException {
        postService.savePost(post,image);
        return "redirect:/admin/home";
    }


    @GetMapping("/editPost/{postPublicId}")
    public String editPost(Model model,@PathVariable String postPublicId)
    {    if(postService.getPost(postPublicId)==null)
        return "notFoundResource";
        model.addAttribute("post",postService.getPost(postPublicId));
        return "admin/editPost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post)
    {
        postService.updatePost(post);
        return "redirect:/admin/home";
    }
    @GetMapping("/deletePost/{postPublicId}")
    public String deletePost(@PathVariable String postPublicId)
    {
        postService.deletePost(postPublicId);
        return "redirect:/admin/home";
    }

    @GetMapping("/categories")
    public String categories(Model model )
    {
        model.addAttribute("categories",categoriesService.getAllCategories());
        return  "admin/categories";
    }


    @GetMapping("/newCategory")
    public String newCategories(Model model)
    {
        model.addAttribute("category",new Category());
        return "admin/newCategory";
    }

    @PostMapping("/saveCategory")
    public String savePost(@ModelAttribute Category category ){
        categoriesService.saveCategory(category);
        return "redirect:/admin/categories";
    }


    @GetMapping("/editCategory/{categoryPublicId}")
    public String editCategory(Model model,@PathVariable String categoryPublicId)
    {    if(categoriesService.getCategory(categoryPublicId)==null)
        return "notFoundResource";
        model.addAttribute("category",categoriesService.getCategory(categoryPublicId));
        return "admin/editCategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category)
    {
        categoriesService.updateCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/deleteCategory/{categoryPublicId}")
    public String deleteCategory(@PathVariable String categoryPublicId)
    {
        categoriesService.deleteCategory(categoryPublicId);
        return "redirect:/admin/categories";
    }


}
