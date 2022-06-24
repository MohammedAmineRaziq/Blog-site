package com.example.blog.controller;

import com.example.blog.Util.Utils;
import com.example.blog.model.Category;
import com.example.blog.model.Comment;
import com.example.blog.model.User;
import com.example.blog.model.UserDetailsImpl;
import com.example.blog.service.CategoryService;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping
public class SharedController {


    @Autowired
    PostService postService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model , @Param("keyword") String keyword)
    {
        model.addAttribute("keyword", keyword);
        model.addAttribute("posts",this.postService.getAllPosts(keyword));
        model.addAttribute("categories",categoryService.getAllCategories());

        return  "/home";
    }

    @GetMapping("/signup")
    public String signUp(HttpServletResponse httpServletResponse, Model model) throws IOException {
        model.addAttribute("user",new User());
        return "SignUp";
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute User user)
    {
        System.out.println("post");
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpServletResponse httpServletResponse) throws IOException {
        Utils.redirctUser(httpServletResponse);
        return "login";
    }


    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/";
    }

    @GetMapping("/post/{postPublicId}")
    public String post(Model model,@PathVariable String postPublicId) throws IOException {

        Object principal =Utils.getAuthenticatedUser();
        if(principal instanceof UserDetailsImpl&&((UserDetailsImpl)principal).getRole().equals("ROLE_USER"))
        {
            model.addAttribute("isAuthenticated",true);
            if(postService.getPost(postPublicId)==null)
                return "notFoundResource";
            model.addAttribute("comment",new Comment());
        }
        else
        {
            model.addAttribute("isAuthenticated",false);
            if(postService.getPost(postPublicId)==null)
                return "notFoundResource";
        }
        model.addAttribute("comments",postService.getPostComments(postPublicId));
        model.addAttribute("post",postService.getPost(postPublicId));
        return "post";
    }

}