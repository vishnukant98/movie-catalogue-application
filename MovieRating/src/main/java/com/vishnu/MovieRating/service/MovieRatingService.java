package com.vishnu.MovieRating.service;

import com.vishnu.MovieRating.MovieRatingApplication;
import com.vishnu.MovieRating.model.MovieRating;
import com.vishnu.MovieRating.model.Ratings;
import com.vishnu.MovieRating.save.SaveMovieDetailsRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieRatingService {

    public Ratings getRatings(int userId){
        return new Ratings(userId,Collections.singletonList(
                new MovieRating(1L,"Vkant","tt1375666","UNWATCHED","4")
        ));

    }

    public void saveMovieDetails(SaveMovieDetailsRequest request) {
        SaveMovieDetails
    }
}
