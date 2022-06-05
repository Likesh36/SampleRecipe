package com.recipe.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.entity.Customer;
//import com.recipe.entity.Customer;
import com.recipe.entity.Recipe;
import com.recipe.entity.User;
import com.recipe.exception.CustomerNotFoundException;
//import com.recipe.exception.CustomerNotFoundException;
//import com.recipe.exception.InvalidUserException;
import com.recipe.exception.RecipeNotFoundException;
import com.recipe.jwt.JwtTokenUtil;
import com.recipe.repository.ICustomerRepository;
import com.recipe.repository.IRecipeRepository;
import com.recipe.repository.IUserRepository;
import com.recipe.services.CustomerService;
import com.recipe.services.IRecipeService;

@RestController
@RequestMapping("/recipe/customer")
public class CustomerRecipeController {

	Logger logger = LoggerFactory.getLogger(CustomerRecipeController.class);

	@Autowired
	IRecipeService recipeService;
	@Autowired
	IRecipeRepository recipeRepository;
	@Autowired
	CustomerService customerService;
	@Autowired
	ICustomerRepository customerRepository;
	@Autowired
	IUserRepository userRepository;

	//boolean isLoggedIn = false;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	User user = null;

	public CustomerRecipeController() {
		logger.info("-----> Inside Customer Recipe Service Controller Working!");
	}

//HTTP request to a server for adding recipe.

	@PostMapping("/addRecipe")
	// The POST method is used when you want to send some data to the server.
	public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe, HttpServletRequest request)
			throws RecipeNotFoundException {

		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		Optional<Recipe> opt = recipeRepository.findByName(recipe.getName());

		if (opt.isPresent()) {
			logger.info("Recipe with ID {} is already EXISTS", recipe.getRecipeId());
			throw new RecipeNotFoundException("Recipe already exists");

		} else {

			recipeService.addRecipe(recipe);
			logger.info("Recipe with ID {} is created SUCCESSFULLY", recipe.getRecipeId());
			return new ResponseEntity<>(" Recipe is added to the list ", HttpStatus.CREATED);

		}
	}

//HTTP request to a server for retrieving recipe by recipeName.

	@GetMapping("/{recipeName}")
	public ResponseEntity<?> getRecipeByName(@PathVariable("recipeName") String recipeName, HttpServletRequest request)
			throws RecipeNotFoundException {

		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		Optional<Recipe> opt = recipeRepository.findByName(recipeName);

		logger.info("Calling Recipe By Name {}", recipeName);

		if (opt.isPresent()) {

			logger.info("Recipe with Name {} is FOUND", recipeName);

			return new ResponseEntity<>(recipeService.getRecipeByName(recipeName), HttpStatus.FOUND);
		}

		else {

			logger.info("Recipe with Name {} not found", recipeName);

			throw new RecipeNotFoundException("Recipe with name : " + recipeName + " is not found ");
		}
	}
	
//HTTP request to a server for registering new Customer
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CustomerNotFoundException{
		//user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
	
		Optional<Customer> opt = userRepository.findByUsename(customer.getUsername());
		
		if (opt.isPresent()) {
			
			logger.info("Customer with user name {} is already exists ",customer.getUsername());
			
			throw new CustomerNotFoundException("Username already exists");
		}
		else {
			customerService.addCustomer(customer);
			
			logger.info("New Customer registered successfully with username {}",customer.getUsername());
			
			return new ResponseEntity<>(" User registered Successfully ", HttpStatus.CREATED);
			
		}
	}

}
