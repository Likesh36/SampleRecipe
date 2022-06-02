package com.recipe.services;

import java.util.List;
import java.util.Optional;

import com.recipe.entity.Recipe;
//import com.recipe.exception.RecipeNotFoundException;

public interface IRecipeService {

	//for calling all recipes record by using the method findAll() of CrudRepository
	public List<Recipe> getRecipesList();

	//for saving a new recipe by using the method save() of CrudRepository
	public Recipe addRecipe(Recipe recipe);

	//for updating the existing recipe by using the method save() of CrudRepository
	public void updateRecipe(Recipe recipe);

	//for deleting the existing recipe by recipeId using the method deleteById() of CrudRepository
	public void deleteRecipe(int recipeId);
	
	//for calling existing recipe by recipeId using method findById().get() of CrudRepository
	public Recipe getRecipe(int recipeId);
	
	//for calling existing recipe by recipeId using method findById() of CrudRepository
	public Optional<Recipe> getRecipeById(int recipeId);

	//for calling existing recipe by recipeName using method findByName() of CrudRepository
	public Optional<Recipe> getRecipeByName(String name);
	
}
