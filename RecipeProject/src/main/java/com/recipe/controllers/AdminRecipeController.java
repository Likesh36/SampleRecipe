package com.recipe.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.entity.Admin;
import com.recipe.entity.Customer;
import com.recipe.entity.Recipe;
import com.recipe.entity.User;
import com.recipe.exception.CustomerNotFoundException;
import com.recipe.exception.InvalidUserException;
import com.recipe.exception.RecipeNotFoundException;
import com.recipe.jwt.JwtTokenUtil;
import com.recipe.repository.IAdminRepository;
import com.recipe.repository.ICustomerRepository;
import com.recipe.repository.IRecipeRepository;
import com.recipe.repository.IUserRepository;
import com.recipe.services.CustomerService;
import com.recipe.services.IAdminService;
import com.recipe.services.IRecipeService;

@RestController
@RequestMapping("/recipes/admin")
public class AdminRecipeController {

	Logger logger = LoggerFactory.getLogger(AdminRecipeController.class);

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
	@Autowired
	IAdminRepository adminRepository;
	@Autowired
	IAdminService adminService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	User user = null;

	public AdminRecipeController() {
		logger.info("-----> Inside Admin Recipe Service Controller Working!");
	}

//HTTP request to a server for retrieving all the recipes from the list.

	@GetMapping("/allRecipes")
	// The GET method is used to retrieve information from the given server using a
	// given URI. Requests using GET should only retrieve data and should have no
	// other effect on the data.
	public ResponseEntity<?> getAllRecipes(HttpServletRequest request) throws RecipeNotFoundException {

		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		logger.info("Calling Recipe List");

		if (!recipeRepository.findAll().isEmpty()) {

			logger.info("Recipe List FOUND");

			return new ResponseEntity<>(recipeService.getRecipesList(), HttpStatus.OK);

		} else {

			logger.info("Recipe List is EMPTY");

			throw new RecipeNotFoundException("No recipes found in the list ");

		}
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

//HTTP Request for updating existing recipe by recipeId.

	@PutMapping("/updateRecipe/{recipeId}")
	// The PUT method is used to request the server to store the included
	// entity-body at a location specified by the given URL.
	public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe, HttpServletRequest request)
			throws RecipeNotFoundException {

		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		logger.info("Updating the recipe with ID {}", recipe.getRecipeId());

		if (recipeRepository.existsById(recipe.getRecipeId())) {

			recipeService.updateRecipe(recipe);
			logger.info(" ID : {} is UPDATED SUCCESSFULLY", recipe.getRecipeId());
			return new ResponseEntity<>(" Recipe Id " + recipe.getRecipeId() + " is successfully updated in the list ",
					HttpStatus.ACCEPTED);

		} else {
			logger.info("Recipe with ID {} is NOT FOUND", recipe.getRecipeId());
			throw new RecipeNotFoundException("Recipe Id with " + recipe.getRecipeId() + " is not found in the list ");
		}

	}

//HTTP Request for deleting existing recipe by recipeId.

	@DeleteMapping("/deleteMapping/{recipeId}")
	// The DELETE method is used to request the server to delete a file at a
	// location specified by the given URL.
	public ResponseEntity<String> deleteRecipe(@PathVariable("recipeId") int recipeId, HttpServletRequest request)
			throws RecipeNotFoundException {

		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		Optional<Recipe> opt = recipeRepository.findById(recipeId);

		if (opt.isPresent()) {

			recipeService.deleteRecipe(recipeId);

			logger.info("ID with {} is DELETED SUCCESSFULLY", recipeId);

			return new ResponseEntity<>("Recipe id : " + recipeId + " is successfully deleted fom the list",
					HttpStatus.OK);
		} else {

			logger.info("ID with {} is not found", recipeId);

			throw new RecipeNotFoundException(" Recipe id : " + recipeId + " is not found in the list ");

		}
	}

//HTTP request to a server for retrieving recipe by recipeId.

	@GetMapping("/getRecipeById/{recipeId}")
	public ResponseEntity<?> getRecipeById(@PathVariable("recipeId") int recipeId, HttpServletRequest request)
			throws RecipeNotFoundException {

		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		Optional<Recipe> opt = recipeRepository.findById(recipeId);

		logger.info("Calling a recipe by ID{ }", recipeId);

		if (opt.isPresent()) {

			logger.info("Recipe with ID {} is found", recipeId);

			return new ResponseEntity<>(recipeService.getRecipeById(recipeId), HttpStatus.OK);

		}

		else {

			logger.info("RecipeID with ID {} is NOT FOUND", recipeId);

			throw new RecipeNotFoundException(" Recipe id : " + recipeId + " is not found from the list ");
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

//HTTP request to a server for retrieving all the customers from the list.
	@GetMapping("/customers")
	public ResponseEntity<?> viewAllCustomers(HttpServletRequest request) throws InvalidUserException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		logger.info("Calling all the customers from the list");
		if (!customerRepository.findAll().isEmpty()) {

			logger.info("Customer list FOUND");
			return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);

		} else {
			logger.info("Customer list NOT FOUND");
			throw new InvalidUserException("No customers found in the list");
		}
	}

//HTTP request to a server for registering new Admin
	@PostMapping("/addAdmin")
	public ResponseEntity<?> addAdmin(@RequestBody Admin admin, HttpServletRequest request)
			throws InvalidUserException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		Optional<Admin> opt = userRepository.findByUsername(admin.getUsername());

		if (opt.isPresent()) {
			logger.info("Admin with user name {} is already exists ", admin.getUsername());
			throw new InvalidUserException("Username already exists");
		} else {
			adminService.addAdmin(admin);
			logger.info("New Admin added successfully with username {} ", admin.getUsername());
			return new ResponseEntity<>("Admin registered Successfully ", HttpStatus.CREATED);
		}

	}

//HTTP request to a server for retrieving all the admins from the list.
	@GetMapping("/admins")
	public ResponseEntity<List<Admin>> viewAllAdmins(HttpServletRequest request) throws InvalidUserException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);

		logger.info("Calling all the Admins from the list");
		if (!adminRepository.findAll().isEmpty()) {

			logger.info("Admin List FOUND");
			return new ResponseEntity<>(adminService.getAllAdmin(), HttpStatus.OK);
		} else {
			logger.info("Admin List is EMPTY");
			throw new InvalidUserException("No Admins Found in the List");
		}
	}

// HTTP request to server for retrieving customer by userId.
	@GetMapping("/getCustomer/{userId}")
	public ResponseEntity<?> viewCustomer(@PathVariable("userId") int userId, HttpServletRequest request)
			throws CustomerNotFoundException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);
		Optional<Customer> opt = customerRepository.findById(userId);
		logger.info("Calling existing customer by userId {} ", userId);
		if (opt.isPresent()) {
			logger.info("Customer with UserId {} is FOUND", userId);
			return new ResponseEntity<>(customerService.getCustomer(userId), HttpStatus.OK);
		} else {
			logger.info("Customer with UserId {} is NOT FOUND", userId);
			throw new CustomerNotFoundException("Customer with UserID : " + userId + " is not found from the list ");
		}
	}

// HTTP request to server for retrieving admin by userId.
	@GetMapping("getAdmin/{userId}")
	public ResponseEntity<?> viewAdmin(@PathVariable("userId") int userId, HttpServletRequest request)
			throws InvalidUserException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);
		Optional<Admin> opt = adminRepository.findById(userId);
		logger.info("Calling existing admin by userId {} ", userId);
		if (opt.isPresent()) {
			logger.info("Admin with UserId {} is FOUND", userId);
			return new ResponseEntity<>(adminService.getAdmin(userId), HttpStatus.OK);
		} else {
			logger.info("Admin with UserId {} is NOT FOUND", userId);
			throw new InvalidUserException("Admin with UserID : " + userId + " is not found from thr list ");
		}
	}

// HTTP request to server for updating existing customer by userId.
	@PutMapping("/updateCustomer/{userId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("userId") int userId, @RequestBody Customer customer,
			HttpServletRequest request) throws CustomerNotFoundException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);
		logger.info("Updating existing customer by userId {} ", userId);
		if (customerRepository.existsById(customer.getUserId())) {
			customerService.updateCustomer(userId, customer);
			logger.info("Customer with UserId : {} is UPDDATED SUCCESSFULLY ", customer.getUserId());
			return new ResponseEntity<>(
					" Customer with UserId " + customer.getUserId() + " is successfully updated in the List",
					HttpStatus.ACCEPTED);
		} else {
			logger.info("Customer with UserId : {} is NOT FOUND", customer.getUserId());
			throw new InvalidUserException(
					"Customer with UserId " + customer.getUserId() + " is not found in the list ");
		}
	}

// HTTP request to server for deleting existing customer by userId.
	@DeleteMapping("/deleteMapping/customer/{userId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("userId") int userId, HttpServletRequest request)
			throws CustomerNotFoundException {
		user = jwtTokenUtil.validateTokenAndGetUserDetails(request);
		logger.info("Deleting Customer with userId {} ", userId);
		Optional<Customer> opt = customerRepository.findById(userId);
		if (opt.isPresent()) {
			customerService.deleteCustomer(userId);
			logger.info("Customer with UserId {} is DELETED SUCCESSFULLY", userId);
			return new ResponseEntity<>("Customer with UserId : " + userId + " is successfully deleted from the list",
					HttpStatus.OK);
		} else {
			logger.info("Customer with UserId {} is NOT FOUND", userId);
			throw new CustomerNotFoundException("Customer with UserId : " + userId + " is not found in the list");
		}
	}
}
