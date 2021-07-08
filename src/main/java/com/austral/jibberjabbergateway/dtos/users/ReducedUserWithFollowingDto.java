package com.austral.jibberjabbergateway.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReducedUserWithFollowingDto {
    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private int followersCount;

    private int followingCount;

    private boolean following;
}
