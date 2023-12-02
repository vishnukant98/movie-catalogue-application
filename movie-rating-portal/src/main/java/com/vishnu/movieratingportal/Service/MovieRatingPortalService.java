package com.vishnu.movieratingportal.Service;

import com.vishnu.movieratingportal.model.Movie;
import com.vishnu.movieratingportal.model.login.Login;
import com.vishnu.movieratingportal.model.top250.Top250Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class MovieRatingPortalService {

    @Autowired
    RestTemplate restTemplate;

    public Movie getMovie(String id){
        log.info("Inside getMovies id: "+id);
        Movie movie = restTemplate.getForObject("http://localhost:8086/v1/movieInfo/"+id,Movie.class);
        return movie;
    }
    public Top250Data get250Movies(){
        log.info("getting 250 movies");
        Top250Data data = restTemplate.getForObject("http://localhost:8086/v1/movieInfo/top250",Top250Data.class);
        return data;
    }

    public ResponseEntity<String> getAuthenticated(Login login, HttpServletRequest request){
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8086/v1/users/login", login, String.class);
            return response;
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
