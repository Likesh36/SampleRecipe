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

import com.recipe.entity.Admin;
import com.recipe.repository.IAdminRepository;
import com.recipe.repository.IUserRepository;
import com.recipe.services.IAdminService;
@SpringBootTest
public class AdminServiceTest {

	@Autowired
	IAdminService adminService;
	@MockBean
	IUserRepository userRepository;
	@MockBean
	IAdminRepository adminRepository;
	
	public static Admin admin,admin1,admin2;
	@BeforeAll
	public static void setUp() {
		admin=new Admin();
		admin1=new Admin();
		admin2=new Admin();
		
		admin.setAdminName("Ramu");
		admin.setAdminContact("9998887766");
		admin.setUserId(1);
			
		admin1.setAdminName("Ranga");
		admin1.setAdminContact("8888999777");
		admin1.setUserId(4);
	}
	@Test
	@DisplayName("Test case of create Admin")
	void createAdminTest() {
		
		adminRepository.save(admin);
		assertNotNull(admin);
		}
	
	@Test
	@DisplayName("Positive Test case of create Admin")
	void createAdminTest1() {
	when(adminRepository.save(admin)).thenReturn(admin);
	assertEquals(admin, adminService.addAdmin(admin));
	}
	
	@Test
	@DisplayName("Negative Test case of create Admin")
	void createAdminTest2() {
	when(adminRepository.save(admin)).thenReturn(admin);
	assertNotEquals(admin1, adminService.addAdmin(admin));
	}
	@Test
	@DisplayName("Positive test case of get all admins")
	void getAllAdminsTest() {
		List<Admin> listAdmin = new ArrayList<>();
		listAdmin.add(admin);
		listAdmin.add(admin1);
		listAdmin.add(admin2);
		when(adminRepository.findAll()).thenReturn(listAdmin);
		assertEquals(listAdmin.size(), adminService.getAllAdmin().size());
	}
	@Test
	@DisplayName("Negative test case of get all admins")
	void getAllAdminsTest1() {
		List<Admin> listAdmin = new ArrayList<>();
		listAdmin.add(admin);
		listAdmin.add(admin1);
		listAdmin.add(admin2);
		when(adminRepository.findAll()).thenReturn(listAdmin);
		assertNotEquals(4, adminService.getAllAdmin().size());
	}
	@Test
	@DisplayName("Positive test case for get Admin")
	void getAdminTest() {
		when(adminRepository.findById(1)).thenReturn(Optional.of(admin1));
		admin=adminService.getAdmin(1);
		assertEquals(admin1.toString(),admin.toString());
	}
	@Test
	@DisplayName("Negative test case for get Admin")
	void getAdminTest1() {
		when(adminRepository.findById(2)).thenReturn(Optional.of(admin1));
		admin=adminService.getAdmin(2);
		assertNotEquals(admin2.toString(),admin.toString());
	}
}
