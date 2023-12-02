package com.vishnu.movieCatalogue.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovieCatalogue {
    private int userId;
    private String username;
    private List<Movie> movies;
}
