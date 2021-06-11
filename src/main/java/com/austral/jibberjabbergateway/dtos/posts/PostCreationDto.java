package com.austral.jibberjabbergateway.dtos.posts;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreationDto {
    @NotNull
    private String content;
}
