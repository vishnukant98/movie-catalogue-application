package com.vishnu.MovieRating.save;

import lombok.Data;

@Data
public class SaveMovieDetails {
    private String id;
    private String username;
    private status movieStatus;
}
enum status{
    WATCHED,
    WANT_TO_WATCH
};
