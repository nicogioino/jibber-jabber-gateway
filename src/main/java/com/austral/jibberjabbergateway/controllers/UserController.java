package com.austral.jibberjabbergateway.controllers;

import com.austral.jibberjabbergateway.clients.UsersClient;
import com.austral.jibberjabbergateway.dtos.users.*;
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

    @PostMapping("/edit")
    public ReducedUserDto editUser(@RequestBody ReducedUserDto editUserDto) {
        return usersClient.editUser(editUserDto);
    }

    @GetMapping("/by-username/{username}")
    public UserProfileDto findByUsername(@PathVariable String username) {
        return usersClient.findByUsername(username);
    }

    @PostMapping("/follow/{username}")
    public void followUser(@PathVariable String username) {
        usersClient.followUser(username);
    }

    @PostMapping("/unfollow/{username}")
    public void unfollowUser(@PathVariable String username) {
        usersClient.unfollowUser(username);
    }

    @PostMapping("/edit/password")
    public void editPassword(@RequestBody EditPasswordDto editPasswordDto) {
        usersClient.editPassword(editPasswordDto);
    }

    @GetMapping("/by-id/{id}")
    public UserProfileDto findById(@PathVariable String id) {
        return usersClient.findById(id);
    }
}
