package com.vishnu.MovieRating.save;

import lombok.Data;

@Data
public class SaveMovieDetailsRequest {
    private String username;
    private String id;
    private String status;
}
