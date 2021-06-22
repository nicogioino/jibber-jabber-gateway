package com.austral.jibberjabbergateway.controllers;

import com.austral.jibberjabbergateway.clients.PostsClient;
import com.austral.jibberjabbergateway.dtos.posts.PostCreationDto;
import com.austral.jibberjabbergateway.dtos.posts.PostInfoDto;
import com.austral.jibberjabbergateway.dtos.posts.PostListingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostsController {

    private final PostsClient postsClient;

    @Autowired
    public PostsController(PostsClient postsClient) {
        this.postsClient = postsClient;
    }

    @GetMapping("/get-all")
    public PostListingDto postListingDto() {
        return postsClient.getAllPosts();
    }

    @PostMapping("/create")
    public PostInfoDto createPost (@RequestBody PostCreationDto postCreationDto) {
        return postsClient.createPost(postCreationDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postsClient.deletePost(id);
    }

    @GetMapping("/by-user/{userId}")
    public PostListingDto getPostsByUserId(@PathVariable String userId) {
        return postsClient.findPostByCreatorId(userId);
    }
}
