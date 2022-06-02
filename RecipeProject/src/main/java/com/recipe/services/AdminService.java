package com.recipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Admin;
import com.recipe.repository.IAdminRepository;

@Service
public class AdminService implements IAdminService{
	
	@Autowired
	IAdminRepository adminRepository;
	//saving new admin by using the method save() of CrudRepository
	@Override
	public Admin addAdmin(Admin admin) {
		adminRepository.save(admin);
		return admin;
	}
	//calling existing admin by userId using method findById().get() of CrudRepository
	@Override
	public Admin getAdmin(int userId) {
		return adminRepository.findById(userId).get();
		
	}
	//calling existing admins record by using the method findAll() of CrudRepository
	@Override
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}
	
	

}
