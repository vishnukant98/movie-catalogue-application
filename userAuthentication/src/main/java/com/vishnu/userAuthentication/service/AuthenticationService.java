package com.vishnu.userAuthentication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vishnu.userAuthentication.Model.UserDTO;
import com.vishnu.userAuthentication.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private String key = "secret";

    public String authenticateUser(String username,String password) throws Exception{
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;



//        Algorithm algorithm = Algorithm.HMAC256(key.getBytes());
//        User user = (User) authentication.getPrincipal();
//        String access_token = "Bearer "+JWT.create().withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
//                .withIssuer(url)
//                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .sign(algorithm);
//
//        return access_token;
    }
}
