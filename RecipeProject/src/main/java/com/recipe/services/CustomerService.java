package com.recipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Customer;
import com.recipe.exception.CustomerNotFoundException;
import com.recipe.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;

	//saving a new customer by using the method save() of CrudRepository
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	
	}
	//updating the existing customer by using the method save() of CrudRepository
	@Override
	public Customer updateCustomer(int userId, Customer customer) throws CustomerNotFoundException {
		customerRepository.findById(userId).orElseThrow(()->new CustomerNotFoundException("Customer detail not found !!!"));
		return customerRepository.save(customer);
	}
	//deleting the existing customer by userId using the method deleteById() of CrudRepository
	@Override
	public Customer deleteCustomer(int userId) throws CustomerNotFoundException {
		Customer customer = getCustomer(userId);
		customerRepository.deleteById(userId);
		return customer;
	}
	//calling existing customer by userId using method findById().get() of CrudRepository
	@Override
	public Customer getCustomer(int userId) throws CustomerNotFoundException {
		return customerRepository.findById(userId).orElseThrow(()->new CustomerNotFoundException("Customer detail not FOUND !!!"));
	}
	//calling all customers list by using the method findAll() of CrudRepository
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	//calling existing customer userId by username using method findByUsername() of CrudRepository
	@Override
	public int getUserIdByUsername(String username) throws CustomerNotFoundException {
		int userId = customerRepository.findByUsername(username).getUserId();
		return userId;
	}
	
}
