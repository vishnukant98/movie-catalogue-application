package com.vishnu.movieinfoservice.movie.service;

import com.vishnu.movieinfoservice.movie.model.Movie;
import com.vishnu.movieinfoservice.movie.model.Movies;
import com.vishnu.movieinfoservice.movie.model.top250.Top250Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public Movie getMovieInfo(String id){
        Movie movie = restTemplate.getForObject(
            "https://imdb-api.com/en/API/Title/"+apiKey+"/"+id+"/Trailer",Movie.class
        );
        return movie;
    }

    public Top250Data getTop250Movies(){
        Top250Data data = restTemplate.getForObject("https://imdb-api.com/en/API/Top250Movies/"+apiKey,
                Top250Data.class);
        return data;
    }
}
