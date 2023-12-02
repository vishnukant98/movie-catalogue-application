package com.vishnu.MovieRating.controller;

import com.vishnu.MovieRating.model.MovieRating;
import com.vishnu.MovieRating.model.Ratings;
import com.vishnu.MovieRating.save.SaveMovieDetailsRequest;
import com.vishnu.MovieRating.service.MovieRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("v1/movies")
public class MovieRatingController {
    @Autowired
    MovieRatingService movieRatingService;

    @GetMapping("/{userId}")
    public Ratings getRating(@PathVariable int userId){
        log.info("MovieRatingController:  "+userId);
        return movieRatingService.getRatings(userId);
    }
    @PostMapping("/save")
    public ResponseEntity saveRating(@RequestBody SaveMovieDetailsRequest request){
        movieRatingService.saveMovieDetails(request);
    }
}
