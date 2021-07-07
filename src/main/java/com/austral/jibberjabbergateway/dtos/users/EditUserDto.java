package com.austral.jibberjabbergateway.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;
}
