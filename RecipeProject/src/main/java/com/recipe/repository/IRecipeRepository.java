package com.recipe.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recipe.entity.Ingredients;
import com.recipe.entity.Recipe;


@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Integer>{

	@Query("SELECT r from Recipe r WHERE r.name=:name ")
	 public Optional<Recipe> findByName(@Param("name") String name);


	

	

	

}
