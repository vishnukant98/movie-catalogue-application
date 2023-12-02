package com.vishnu.movieCatalogue.service;

import com.vishnu.movieCatalogue.model.Movie;
import com.vishnu.movieCatalogue.model.MovieCatalogue;
import com.vishnu.movieCatalogue.model.MovieRating;
import com.vishnu.movieCatalogue.model.Ratings;
import com.vishnu.movieCatalogue.model.login.Login;
import com.vishnu.movieCatalogue.model.top250.Top250Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class MovieCatalogueService {

    @Autowired
    RestTemplate restTemplate;
    public MovieCatalogue getMovies(int userId){
        /*List<Movie> movies = Arrays.asList(
                new Movie(1,"Test","Desc",3)
        );*/
        Ratings rating = restTemplate.getForObject("http://movie-rating/v1/rating/"+userId,Ratings.class);
        List<Movie> list = new ArrayList<>();
        MovieCatalogue movieCat = new MovieCatalogue(userId,"Test",list);
        for(MovieRating mr:rating.getMovieRating()){
            Movie movie = restTemplate.getForObject("http://movie-info-service/v1/movieInfo/"+mr.getId(),Movie.class);
            //movie.setRating(mr.getRating());
            movieCat.getMovies().add(movie);
        }

        return movieCat;

    }

    public Movie getMovie(String id){
        Movie movie = restTemplate.getForObject("http://movie-info-service/v1/movieInfo/"+id,Movie.class);
        movie.setStatus("Not Watched");
        return movie;
    }
    public Top250Data top250Movies(){
        Top250Data data = restTemplate.getForObject("http://movie-info-service/v1/movieInfo/top250",Top250Data.class);
        return data;
    }
    public ResponseEntity<?> getAuthentication(HttpServletRequest request,Login login){
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://user-authentication-service/users/login", login, String.class);
            log.info("Got Response");
            return response;
        }
        catch(Exception e){
            log.info("Exception: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }


    }
}
