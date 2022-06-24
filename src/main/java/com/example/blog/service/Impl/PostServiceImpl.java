package com.example.blog.service.Impl;

import com.example.blog.Util.Utils;
import com.example.blog.model.Post;
import com.example.blog.model.Comment;
import com.example.blog.model.UserDetailsImpl;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postrepository;

    @Autowired
    Utils utils;

    @Autowired
    UserRepository UserRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Post savePost(Post post, MultipartFile image) throws IOException {
        String nomImage=utils.genereteRandomString(30);

        String nomOrigine=image.getOriginalFilename().toString();
        String[] tokens=nomOrigine.split("[.]");
        String extensionImage=tokens[tokens.length-1];


        post.setPublicId(utils.genereteRandomString(30));
        post.setDate(LocalDate.now());
        post.setImage("/upload/"+nomImage+"."+extensionImage);
        image.transferTo(new File(FileSystems.getDefault().getPath("").toAbsolutePath().toString()+"\\src\\main\\resources\\upload\\"+nomImage+"."+extensionImage));
        return postrepository.save(post);
    }

    @Override
    public Post getPost(String postPublicId) {

        return postrepository.findByPublicId(postPublicId);
    }

    @Override
    public Post updatePost(Post post) {
        Post post1=postrepository.findByPublicId(post.getPublicId());
        post1.setTitre(post.getTitre());
        post1.setContenu(post.getContenu());
        return postrepository.save(post1);
    }

    @Override
    public List<Post> getAllPosts(String keyword) {

        if (keyword != null) {
            return postrepository.search(keyword);
        }
        return postrepository.findAll();
    }

    @Override
    public void deletePost(String postPublicId) {
        postrepository.deleteById(postrepository.findByPublicId(postPublicId).getId());
    }

    @Override
    public List<Comment> getPostComments(String postPublicId) {
        return postrepository.findByPublicId(postPublicId).getComments();
    }

    @Override
    public void addCommentToPost(Comment comment, String postPublicId) {
        UserDetailsImpl user=(UserDetailsImpl)Utils.getAuthenticatedUser();
        comment.setUser(UserRepository.findByEmail(user.getEmail()));
        comment.setPost(postrepository.findByPublicId(postPublicId));
        comment.setDate(LocalDate.now());
        comment.setPublicId(utils.genereteRandomString(30));
        commentRepository.save(comment);
    }




}
