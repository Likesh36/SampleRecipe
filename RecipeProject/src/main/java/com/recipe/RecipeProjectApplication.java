package com.recipe;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.recipe.entity.User;
import com.recipe.repository.IUserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.recipe")
public class RecipeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeProjectApplication.class, args);
	}
	
	@Autowired
	private IUserRepository userRepository;
	
	User user;
	
	@PostConstruct
	public void initUsers()
	{
		   List<User> users = Stream.of(
	                new User(101, "rahul", "rahulpassword", "irahul@gmail.com"),
	                new User(102, "likesh", "likeshpassword", "rlikesh@gmail.com"),
	                new User(103, "ramana", "ramanapassword", "sramana@gmail.com"),
	                new User(104, "mohit", "mohitpassword", "lmohit@gmail.com")
	        ).collect(Collectors.toList());
	        userRepository.saveAll(users);
	}

}
