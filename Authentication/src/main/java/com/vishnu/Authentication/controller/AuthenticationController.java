package com.vishnu.Authentication.controller;

import com.vishnu.Authentication.model.AuthenticationRequest;
import com.vishnu.Authentication.model.AuthenticationResponse;
import com.vishnu.Authentication.service.MyUserDetailsService;
import com.vishnu.Authentication.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/authenticate/{username}&{password}")
    public ResponseEntity<?> authenticate(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        log.info("creds: "+username+" "+password);
        System.out.println("hello");
        AuthenticationResponse authenticationResponse = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch(BadCredentialsException e){
            log.info("invalid Credentials");
            throw new Exception("Invalid Credentials");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
