package com.vishnu.movieCatalogue.model.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class Login {
    private String username;
    private String password;
}
