package com.austral.jibberjabbergateway.dtos.posts;

import com.austral.jibberjabbergateway.dtos.users.ReducedUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInfoDto {

    private Long id;

    LocalDateTime createdTime;

    String content;

    int likes;

    int dislikes;

    ReducedUserDto reducedUserDto;
}
