package com.recipe.services;

import java.util.List;

import com.recipe.entity.Admin;

public interface IAdminService {

	//for saving new admin by using the method save() of CrudRepository
	public Admin addAdmin(Admin admin); 
	//for calling existing admin by userId using method findById().get() of CrudRepository
	public Admin getAdmin(int userId);
	//for calling existing admins record by using the method findAll() of CrudRepository
	public List<Admin> getAllAdmin();
}
