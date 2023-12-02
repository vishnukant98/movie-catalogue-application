package com.vishnu.movieinfoservice.movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movies {
    private String id;
    private String title;
    private String image;
    private String imDbRating;
}
