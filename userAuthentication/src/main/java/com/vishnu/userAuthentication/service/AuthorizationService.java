package com.vishnu.userAuthentication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vishnu.userAuthentication.Model.UserDTO;
import com.vishnu.userAuthentication.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationService {

    private final UserDetailsService userService;
    private final JwtUtil jwtUtil;
    public UserDTO authorize(String token){
        String username = jwtUtil.extractUsername(token);
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userService.loadUserByUsername(username);
            if(jwtUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                return new UserDTO(username,token);
            }
        }
        return new UserDTO(username,token);

//        try {
//            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT verify = verifier.verify(token);
//            String username = verify.getSubject();
//            String[] roles = verify.getClaim("roles").asArray(String.class);
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            Arrays.stream(roles).forEach(x -> {
//                authorities.add(new SimpleGrantedAuthority(x));
//            });
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(username, null, authorities);
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            return true;
//        }
//        catch (Exception e){
//            log.info("Error in logging "+e.getMessage());
//            return false;
//        }
    }
}
