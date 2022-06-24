package com.example.blog.controller;


import com.example.blog.model.Comment;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    PostService postService;


    @PostMapping("/Post/addcomment/{postPublicId}")
    public String addCommentToPost(@ModelAttribute Comment comment, @PathVariable String postPublicId )
    {
        postService.addCommentToPost(comment,postPublicId);
        return "redirect:/post/"+postPublicId;
    }
}