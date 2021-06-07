package com.austral.jibberjabbergateway.controllers;

import com.austral.jibberjabbergateway.clients.UsersClient;
import com.austral.jibberjabbergateway.dtos.CreateUserDto;
import com.austral.jibberjabbergateway.dtos.ReducedUserDto;
import com.austral.jibberjabbergateway.dtos.UserListingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersClient usersClient;

    @Autowired
    public UserController(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @GetMapping("/get-users")
    private UserListingDto getUsers() {
        return usersClient.getAllUsers();
    }

    @GetMapping("/{id}")
    private ReducedUserDto getUserById(@PathVariable String id) {
        return usersClient.getUserById(id);
    }

    @PostMapping("/register")
    private ReducedUserDto register (@RequestBody CreateUserDto createUserDto) {
        return usersClient.createUser(createUserDto);
    }
}
