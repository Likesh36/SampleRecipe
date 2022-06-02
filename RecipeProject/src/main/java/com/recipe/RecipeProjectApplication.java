package com.recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.recipe.entity.Admin;
import com.recipe.entity.Customer;
import com.recipe.entity.User;
import com.recipe.repository.IAdminRepository;
import com.recipe.repository.ICustomerRepository;
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
	                new Admin("likesh","likeshpassword","admin","Likesh","8889997722"),
	                new Customer("krishna","krishnapassword","customer","Krishna","7779998811"),
	                new Customer("customer1", "customer123", "customer", "customer1", "9988776655", "customer@gmail.com", LocalDate.of(1999, 8, 10), "male", "Indian")
	        ).collect(Collectors.toList());
	        userRepository.saveAll(users);
	}

}
