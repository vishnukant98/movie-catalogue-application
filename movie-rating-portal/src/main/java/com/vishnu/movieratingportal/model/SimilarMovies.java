package com.vishnu.movieratingportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimilarMovies {
    private String id;
    private String title;
    private String image;
    private String imDbRating;
}
