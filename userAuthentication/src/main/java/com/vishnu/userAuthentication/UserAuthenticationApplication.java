package com.vishnu.userAuthentication;

import com.vishnu.userAuthentication.Model.AppUser;
import com.vishnu.userAuthentication.Model.Role;
import com.vishnu.userAuthentication.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableEurekaClient
public class UserAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser((new AppUser(null,"Vishnu","vkant","pass",new ArrayList<>())));
			userService.saveUser((new AppUser(null,"Saurabh","user1","pass",new ArrayList<>())));
			userService.saveUser((new AppUser(null,"Harshit","user2","pass",new ArrayList<>())));
			userService.saveUser((new AppUser(null,"Sarvesh","user3","pass",new ArrayList<>())));

			userService.addRoleToUser("vkant","ROLE_ADMIN");
			userService.addRoleToUser("user1","ROLE_USER");
			userService.addRoleToUser("user2","ROLE_USER");

		};
	}
}
