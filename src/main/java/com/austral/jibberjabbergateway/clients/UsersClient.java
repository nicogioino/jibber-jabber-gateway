package com.austral.jibberjabbergateway.clients;

import com.austral.jibberjabbergateway.dtos.users.CreateUserDto;
import com.austral.jibberjabbergateway.dtos.users.ReducedUserDto;
import com.austral.jibberjabbergateway.dtos.users.UserListingDto;
import com.austral.jibberjabbergateway.security.TokenUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsersClient {
    private final String USER_SERVICE_URL = "http://jibber-jabber-user:8081/users";
    private final RestTemplate restTemplate;
    private final TokenUtils tokenUtils;

    public UsersClient(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
        this.restTemplate = new RestTemplate();
    }

    public UserListingDto getAllUsers() {
        String url =  USER_SERVICE_URL + "/get-all";
        ResponseEntity<UserListingDto> response = restTemplate.getForEntity(url,UserListingDto.class);
        return response.getBody();
    }

    public ReducedUserDto getUserById(String userId) {
        String url = USER_SERVICE_URL + "/" +userId;
        ResponseEntity<ReducedUserDto> response = restTemplate.getForEntity(url,ReducedUserDto.class);
        return response.getBody();
    }

    public ReducedUserDto createUser(CreateUserDto createUserDto) {
        String url = USER_SERVICE_URL + "/register";
        ResponseEntity<ReducedUserDto> response = restTemplate.postForEntity(url,createUserDto,ReducedUserDto.class);
        return response.getBody();
    }

    public ReducedUserDto editUser(ReducedUserDto editedUser) {
        String userId = tokenUtils.getLoggedUser().getId();
        String url = USER_SERVICE_URL + "/edit/" + userId;
        ResponseEntity<ReducedUserDto> response =  restTemplate.postForEntity(url,editedUser,ReducedUserDto.class);
        return response.getBody();
    }

    public ReducedUserDto findByUsername (String username) {
        String url = USER_SERVICE_URL + "/by-username/" + username;
        ResponseEntity<ReducedUserDto> response = restTemplate.getForEntity(url,ReducedUserDto.class);
        return response.getBody();
    }
}
