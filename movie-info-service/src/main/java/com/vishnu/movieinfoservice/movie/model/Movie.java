package com.vishnu.movieinfoservice.movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    private String id;
    private String title;
    private String plot;
    private String image;
    private String genres;
    private String stars;
    private String imDbRating;
    private List<Movies> similars;
}
