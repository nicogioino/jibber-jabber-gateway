package com.austral.jibberjabbergateway.clients;

import com.austral.jibberjabbergateway.dtos.users.CreateUserDto;
import com.austral.jibberjabbergateway.dtos.users.ReducedUserDto;
import com.austral.jibberjabbergateway.dtos.users.UserListingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsersClient {
    private final String USER_SERVICE_URL = "http://localhost:8081/users";
    private final RestTemplate restTemplate;

    public UsersClient() {
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
}
