package com.vishnu.movieCatalogue.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
    private String status;
    private List<SimilarMovies> similars;
}
