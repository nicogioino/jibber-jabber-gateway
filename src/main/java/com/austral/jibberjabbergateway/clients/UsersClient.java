package com.austral.jibberjabbergateway.clients;

import com.austral.jibberjabbergateway.dtos.posts.PostCreationDto;
import com.austral.jibberjabbergateway.dtos.users.*;
import com.austral.jibberjabbergateway.repositories.UserRepository;
import com.austral.jibberjabbergateway.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsersClient {
    private final String USER_SERVICE_URL = "http://jibber-jabber-user:8080/users";
    private final RestTemplate restTemplate;
    private final TokenUtils tokenUtils;
    private final UserRepository userRepository;

    @Autowired
    public UsersClient(TokenUtils tokenUtils, UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;
        this.restTemplate = new RestTemplate();
    }

    public UserListingDto getAllUsers() {
        String url = USER_SERVICE_URL + "/get-all";
        ResponseEntity<UserListingDto> response = restTemplate.getForEntity(url, UserListingDto.class);
        return response.getBody();
    }

    public ReducedUserDto getUserById(String userId) {
        String url = USER_SERVICE_URL + "/" + userId;
        ResponseEntity<ReducedUserDto> response = restTemplate.getForEntity(url, ReducedUserDto.class);
        return response.getBody();
    }

    public ReducedUserDto createUser(CreateUserDto createUserDto) {
        String url = USER_SERVICE_URL + "/register";
        ResponseEntity<ReducedUserDto> response = restTemplate.postForEntity(url, createUserDto, ReducedUserDto.class);
        return response.getBody();
    }

    public ReducedUserDto editUser(EditUserDto editedUser) {
        String userId = tokenUtils.getLoggedUser().getId();
        String url = USER_SERVICE_URL + "/edit/" + userId;
        ResponseEntity<ReducedUserDto> response = restTemplate.postForEntity(url, editedUser, ReducedUserDto.class);
        return response.getBody();
    }

    public UserProfileDto findByUsername(String username) {
        String url = USER_SERVICE_URL + "/by-username/" + username;
        String userId = tokenUtils.getLoggedUser().getId();
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("userId", userId);
        HttpEntity<PostCreationDto> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<UserProfileDto> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity,UserProfileDto.class);
        return response.getBody();
    }

    public void followUser(String userToFollow) {
        String url = USER_SERVICE_URL + "/follow";
        String userId = tokenUtils.getLoggedUser().getId();
        String userToFollowId = userRepository.findByUsername(userToFollow)
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
                .getId();
        FollowUserRequestDto requestDto = new FollowUserRequestDto(userId, userToFollowId);
        restTemplate.postForEntity(url, requestDto, Void.class);
    }

    public void unfollowUser(String userToUnfollow) {
        String url = USER_SERVICE_URL + "/follow";
        String userId = tokenUtils.getLoggedUser().getId();
        String userToUnfollowId = userRepository.findByUsername(userToUnfollow)
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
                .getId();
        FollowUserRequestDto requestDto = new FollowUserRequestDto(userId, userToUnfollowId);
        restTemplate.postForEntity(url, requestDto, Void.class);
    }
}
