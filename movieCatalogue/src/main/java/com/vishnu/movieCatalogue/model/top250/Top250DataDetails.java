package com.vishnu.movieCatalogue.model.top250;

import lombok.Data;

@Data
public class Top250DataDetails {
    public String id;
    public String rank;
    public String title;
    public String fullTitle;
    public String year;
    public String image;
    public String crew;
    public String imDbRating;
}
