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
    List<ReducedUserWithFollowingDto> users;
}
