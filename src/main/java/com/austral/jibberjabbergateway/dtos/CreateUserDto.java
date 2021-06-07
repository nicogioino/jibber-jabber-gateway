package com.austral.jibberjabbergateway.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;
}
