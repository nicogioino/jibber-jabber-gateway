package com.austral.jibberjabbergateway.dtos.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListingDto {

    List<PostInfoDto> posts;

}
