package com.recipe.services;

import java.util.List;

import com.recipe.entity.Customer;
import com.recipe.exception.CustomerNotFoundException;

public interface ICustomerService {
	//for saving a new customer by using the method save() of CrudRepository
	public Customer addCustomer(Customer customer);
	//for updating the existing customer by using the method save() of CrudRepository
	public Customer updateCustomer(int userId, Customer customer) throws CustomerNotFoundException;
	//for deleting the existing customer by userId using the method deleteById() of CrudRepository
	public Customer deleteCustomer(int userId) throws CustomerNotFoundException;
	//for calling existing customer by userId using method findById().get() of CrudRepository
	public Customer getCustomer(int userId) throws CustomerNotFoundException;
	//for calling all customers list by using the method findAll() of CrudRepository
	public List<Customer> getAllCustomers();
	//for calling existing customer by username using method findByUsername() of CrudRepository
	public int getUserIdByUsername(String username) throws CustomerNotFoundException;
	
}
