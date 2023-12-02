package com.vishnu.movieCatalogue.Controller;

import com.vishnu.movieCatalogue.model.Movie;
import com.vishnu.movieCatalogue.model.MovieCatalogue;
import com.vishnu.movieCatalogue.model.login.Login;
import com.vishnu.movieCatalogue.model.top250.Top250Data;
import com.vishnu.movieCatalogue.service.MovieCatalogueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/v1")
public class MovieCatalogueController {
    @Autowired
    MovieCatalogueService movieCatalogues;

    @GetMapping("/movies/{userId}")
    public MovieCatalogue getAllMovies(@PathVariable int userId){
        log.info("MovieCatalogueController:  "+userId);
        return movieCatalogues.getMovies(userId);
    }
    @GetMapping("/movie/{movieId}")
    public Movie getMovie(@PathVariable String movieId){
        log.info("Fetching Movie details for id: "+movieId);
        return movieCatalogues.getMovie(movieId);
    }
    @GetMapping("/movies/top250")
    public Top250Data getTop250(){
        log.info("fetching top 250");
        Top250Data data =  movieCatalogues.top250Movies();
        log.info(data.getItems().size()+"  Size");
        return data;
    }

    @PostMapping(path = "/users/login",consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getAuthenticated(HttpServletRequest request, @RequestBody Login login){
        log.info(login.getUsername());
        return movieCatalogues.getAuthentication(request,login);
    }

}
