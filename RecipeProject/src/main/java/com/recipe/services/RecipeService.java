package com.recipe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;
import com.recipe.repository.IRecipeRepository;
import com.recipe.services.IRecipeService;

@Service
public class RecipeService implements IRecipeService {

	
	@Autowired
	private IRecipeRepository recipeRepository;
	
	
	// calling all recipes record by using the method findAll() of CrudRepository
	@Override
	public List<Recipe> getRecipesList() {
		List<Recipe> listOfRecipes = recipeRepository.findAll();
		return listOfRecipes;
			
	}
	
	//saving a new recipe by using the method save() of CrudRepository
	@Override
	public Recipe addRecipe(Recipe recipe) {
		
		return recipeRepository.save(recipe);
		
	}
	
	//updating the existing recipe by using the method save() of CrudRepository
	public void updateRecipe(Recipe recipe)  {
		
		recipeRepository.save(recipe);
				
	}

	//deleting the existing recipe by recipeId using the method deleteById() of CrudRepository
	@Override
	public void deleteRecipe(int recipeId) {
		
		recipeRepository.deleteById(recipeId);
	}
	
	//calling existing recipe by recipeId using method findById().get() of CrudRepository
	@Override
	public Recipe getRecipe(int recipeId) {
		
		return recipeRepository.findById(recipeId).get();
			
	}
	
	//calling existing recipe by recipeId using method findById() of CrudRepository
	@Override
	public Optional<Recipe> getRecipeById(int recipeId) {
		
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		
		return recipe;
		
	}
	
	//calling existing recipe by recipeName using method findByName() of CrudRepository
	@Override
	public Optional<Recipe> getRecipeByName(String name) {
		
		Optional<Recipe> recipe = recipeRepository.findByName(name);
		
		return recipe;
		
	}

}
