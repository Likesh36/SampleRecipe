package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.Admin;

public interface IAdminRepository extends JpaRepository<Admin,Integer>{

}
