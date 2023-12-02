package com.vishnu.movieratingportal.model;

import lombok.Data;

@Data
public class SearchedMovie {
    private String id;
    private String fullTitle;
    private String year;
    private String image;
    private String crew;
    private String imDbRating;
}
