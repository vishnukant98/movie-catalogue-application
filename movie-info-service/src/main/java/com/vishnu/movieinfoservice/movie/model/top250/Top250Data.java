package com.vishnu.movieinfoservice.movie.model.top250;

import lombok.Data;

import java.util.List;

@Data
public class Top250Data {
    private List<Top250DataDetails> items;
    private String errorMessage;
}
