package com.abhishek.joblisting.controller;

import com.abhishek.joblisting.model.Post;
import com.abhishek.joblisting.repository.PostRepository;
import com.abhishek.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/search/{text}")
    public List<Post> search(@PathVariable String text) {
        System.out.println("Years of experience: " + text);
        return searchRepository.findByText(text);
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/update/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setProfile(updatedPost.getProfile());
            post.setDesc(updatedPost.getDesc());
            post.setExp(updatedPost.getExp());
            post.setTechs(updatedPost.getTechs());
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with ID: " + id);
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable String id) {
        postRepository.deleteById(id);
        return "Post with ID: " + id + " has been deleted successfully!";
    }
}


