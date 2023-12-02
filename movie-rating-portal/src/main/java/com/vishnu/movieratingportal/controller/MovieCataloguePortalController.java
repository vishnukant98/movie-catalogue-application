package com.vishnu.movieratingportal.controller;

import com.vishnu.movieratingportal.Service.MovieRatingPortalService;
import com.vishnu.movieratingportal.model.Movie;
import com.vishnu.movieratingportal.model.login.Login;
import com.vishnu.movieratingportal.model.top250.Top250Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/movies")
@Slf4j
public class MovieCataloguePortalController {

    @Autowired
    MovieRatingPortalService service;
    @RequestMapping("/home/{id}")
    public String getHomePage(@PathVariable("id") String movieId, Model model,HttpServletRequest request){
        log.info("inside Controller");
        List<String> header =  (List<String>)request.getSession().getAttribute(HttpHeaders.AUTHORIZATION);
        log.info("in watched : "+header);

        Movie movie = service.getMovie(movieId);
        log.info(movie.getTitle());
        model.addAttribute("movie",movie);
        return "Test";
    }
    @RequestMapping("/home")
    public String get250Movies(Model model, HttpServletRequest request){
        log.info("fetching 250 movies in Movie_rating_portal");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("movies "+header);
        Top250Data data = service.get250Movies();
        log.info("size"+(data.getItems())+data.getErrorMessage());
        model.addAttribute("movies",data);
        return "top250";
    }
    @RequestMapping("/movies/watched")
    public String getWatched(HttpServletRequest request, HttpServletResponse response, Model model){
        List<String> header =  (List<String>)request.getSession().getAttribute(HttpHeaders.AUTHORIZATION);
        log.info("in watched : "+header);
        if(header==null){
            model.addAttribute("login",new Login());
            return "login";
        }
        //model.addAttribute("AUTHORIZATION","hello World");
        Top250Data data = service.get250Movies();
        log.info("size"+(data.getItems())+data.getErrorMessage());
        model.addAttribute("movies",data);
        return "top250";
    }
    @PostMapping("/authenticate")
    public String getAuthenticated(@ModelAttribute Login login, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        log.info(login.getUsername());
        log.info(login.getPassword());
        ResponseEntity<?> response1 = service.getAuthenticated(login,request);
        request.getSession().setAttribute(HttpHeaders.AUTHORIZATION,response1.getHeaders().get(HttpHeaders.AUTHORIZATION));
        log.info(response1.getStatusCode()+"  Status Code");
        //response.setHeader(AUTHENTICATION_HEADER)
        log.info(response1.getHeaders().get(HttpHeaders.AUTHORIZATION)+"  authorization");
        //response.sendRedirect("/home");
        return "hello";
    }
}
