package com.vishnu.userAuthentication.controller;

import com.vishnu.userAuthentication.Model.AppUser;
import com.vishnu.userAuthentication.Model.Login;
import com.vishnu.userAuthentication.Model.Role;
import com.vishnu.userAuthentication.Model.UserDTO;
import com.vishnu.userAuthentication.service.AuthenticationService;
import com.vishnu.userAuthentication.service.AuthorizationService;
import com.vishnu.userAuthentication.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> saveUser(@RequestBody RoleToUserForm form){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/addtouser").toUriString());
        userService.addRoleToUser(form.getUsername(),form.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(HttpServletResponse response,@RequestBody Login login){
        String username = login.getUsername();
        String password = login.getPassword();
        log.info(username+" is being Authenticated");
        try {
            String token = "Bearer "+authenticationService.authenticateUser(username, password);
            response.setHeader(HttpHeaders.AUTHORIZATION,token);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(APPLICATION_JSON);
            header.set("Accept",APPLICATION_JSON_VALUE);
            header.set("Authentication",token);
            HttpEntity<String> httpEntity = new HttpEntity<>(header);



        }
        catch (Exception e){
            log.info("Bad Credentials");
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok().build();

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authUser(@RequestParam String token){
        log.info("Authenticating: "+token);
        try {
            UserDTO user = authorizationService.authorize(token);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authorizationService.authorize(token));
    }
}

@Data
class RoleToUserForm{
    private String username;
    private String password;
}
