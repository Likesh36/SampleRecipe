package com.recipe.services;

import java.util.List;
import java.util.Optional;

import com.recipe.entity.Recipe;
//import com.recipe.exception.RecipeNotFoundException;

public interface IRecipeService {

	public List<Recipe> getRecipesList();

	public Recipe addRecipe(Recipe recipe);

	public void updateRecipe(Recipe recipe);

	public void deleteRecipe(int recipeId);
	
	public Recipe getRecipe(int recipeId);
	
	public Optional<Recipe> getRecipeById(int recipeId);

	public Optional<Recipe> getRecipeByName(String name);
	
	



}
