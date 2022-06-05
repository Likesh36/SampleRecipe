package com.recipe;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.recipe.entity.Customer;
import com.recipe.exception.CustomerNotFoundException;
import com.recipe.repository.ICustomerRepository;
import com.recipe.repository.IUserRepository;
import com.recipe.services.ICustomerService;

@SpringBootTest
class CustomerServiceTest {

	@Autowired
	ICustomerService customerService;
	@MockBean
	IUserRepository userRepository;
	@MockBean
	ICustomerRepository customerRepository;

	public static Customer customer,customer1,customer2;
	@BeforeAll
	public static void setUp() {
		customer=new Customer();
		customer1=new Customer();
		customer2=new Customer();
		
		customer.setCustomerName("Radha");
		customer.setMobileNumber("7777888999");
		customer.setUserId(1);
		
		customer.setCustomerName("Pradeep");
		customer1.setMobileNumber("6666777888");
		customer1.setUserId(4);
		
	}
	
	@Test
	@DisplayName("Test case of create customer")
	void CreateCustomerTest() {
		customerRepository.save(customer);
		assertNotNull(customer);
	}

	@Test
	@DisplayName("Positive Test case of create Customer")
	void createCustomerTest1() {
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.addCustomer(customer));
	}
	@Test
	@DisplayName("Negative Test case of create Customer")
	void createCustomerTest2() {
		when(customerRepository.save(customer)).thenReturn(customer);
		assertNotEquals(customer2, customerService.addCustomer(customer));
	}

	@Test
	@DisplayName("Positive test case of get all customers")
	void getAllCustomersTest1() {
		List<Customer> listCustomer = new ArrayList<>();
		listCustomer.add(customer);
		listCustomer.add(customer1);
		listCustomer.add(customer2);
		when(customerRepository.findAll()).thenReturn(listCustomer);
		assertEquals(listCustomer.size(),customerService.getAllCustomers().size());
	}
	@Test
	@DisplayName("Negative test case of get all customers")
	void getAllCustomersTest2() {
		List<Customer> listCustomer = new ArrayList<>();
		listCustomer.add(customer);
		listCustomer.add(customer1);
		listCustomer.add(customer2);
		when(customerRepository.findAll()).thenReturn(listCustomer);
		assertNotEquals(4,customerService.getAllCustomers().size());
	}

	@Test
	@DisplayName("Positive test case for get Customer")
	void getCustomerTest() throws CustomerNotFoundException {
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
		customer=customerService.getCustomer(1);
		assertEquals(customer1.toString(),customer.toString());
	}
	@Test
	@DisplayName("Negative test case for get Customer")
	void getCustomerTest1() throws CustomerNotFoundException{
		when(customerRepository.findById(2)).thenReturn(Optional.of(customer1));
		customer=customerService.getCustomer(2);
		assertNotEquals(customer2.toString(),customer.toString());
	}
}
