package com.builterdev.apiblog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private List<PostDto> posts;
    private int TotalPages;
    private long TotalElements;
    private int Size;
    private boolean isLast;

}
