package com.austral.jibberjabbergateway.dtos.users;


import com.austral.jibberjabbergateway.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListingDto {

    List<ReducedUserDto> users;

    public static UserListingDto fromUsersList (List<AppUser> appUsers) {
        List<ReducedUserDto> userDtos =  appUsers.stream().map(ReducedUserDto::fromUser).collect(Collectors.toList());
        return new UserListingDto(userDtos);
    }
}
