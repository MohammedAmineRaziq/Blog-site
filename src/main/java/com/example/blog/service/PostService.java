package com.example.blog.service;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    Post savePost(Post post,  MultipartFile image) throws IOException;

    List<Post> getAllPosts(String Keyword);

    Post getPost(String postPublicId);

    Post updatePost(Post post);

    void deletePost(String postPublicId);

    List<Comment> getPostComments(String postPublicId);

    void addCommentToPost(Comment comment, String postPublicId);

}
