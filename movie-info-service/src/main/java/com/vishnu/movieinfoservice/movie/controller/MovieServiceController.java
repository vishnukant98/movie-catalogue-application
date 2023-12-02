package com.vishnu.movieinfoservice.movie.controller;

import com.vishnu.movieinfoservice.MovieInfoServiceApplication;
import com.vishnu.movieinfoservice.movie.model.Movie;
import com.vishnu.movieinfoservice.movie.model.Movies;
import com.vishnu.movieinfoservice.movie.model.top250.Top250Data;
import com.vishnu.movieinfoservice.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/movieInfo")
@Slf4j
public class MovieServiceController {
    @Autowired
    MovieService movieService;

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") String id){
        log.info("inside Movie Info Controller: "+id);
        return movieService.getMovieInfo(id);
    }
    @GetMapping("/top250")
    public Top250Data getMovies(){
        log.info("Fetching top 250 movies");
        return movieService.getTop250Movies();
    }
}
