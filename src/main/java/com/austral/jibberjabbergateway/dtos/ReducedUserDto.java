package com.austral.jibberjabbergateway.dtos;


import com.austral.jibberjabbergateway.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReducedUserDto {

    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    public static ReducedUserDto fromUser(AppUser appUser) {
        return new ReducedUserDto(appUser.getId(), appUser.getFirstName(), appUser.getLastName(), appUser.getUsername(), appUser.getEmail());
    }
}
