package com.austral.jibberjabbergateway.clients;

import com.austral.jibberjabbergateway.dtos.posts.PostCreationDto;
import com.austral.jibberjabbergateway.dtos.posts.PostInfoDto;
import com.austral.jibberjabbergateway.dtos.posts.PostListingDto;
import com.austral.jibberjabbergateway.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostsClient {
    private final String POST_SERVICE_URL = "http://jibber-jabber-posts:8080/post";
    private final RestTemplate restTemplate;
    private final TokenUtils tokenUtils;

    public PostsClient(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
        restTemplate = new RestTemplate();
    }

    public PostInfoDto createPost(PostCreationDto postCreationDto) {
        String url = POST_SERVICE_URL + "/create";
        String userId = tokenUtils.getLoggedUser().getId();

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("userId", userId);
        HttpEntity<PostCreationDto> httpEntity = new HttpEntity<>(postCreationDto, headers);

        ResponseEntity<PostInfoDto> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PostInfoDto.class);
        return response.getBody();
    }

    public PostListingDto getAllPosts() {
        String url = POST_SERVICE_URL + "/get-all";
        ResponseEntity<PostListingDto> response = restTemplate.getForEntity(url, PostListingDto.class);
        return response.getBody();
    }

    public void deletePost(Long postId) {
        String url = POST_SERVICE_URL + "/delete/" + postId;
        restTemplate.delete(url);
    }

    public PostListingDto findPostByCreatorId(String creatorId) {
        String userId = tokenUtils.getLoggedUser().getId();
        String url = POST_SERVICE_URL + "/by-user/" + userId;
        ResponseEntity<PostListingDto> response = restTemplate.getForEntity(url,PostListingDto.class);
        return response.getBody();
    }
}
