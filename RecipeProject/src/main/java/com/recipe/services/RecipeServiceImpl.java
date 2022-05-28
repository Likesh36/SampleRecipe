package com.recipe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;
import com.recipe.repository.IRecipeRepository;
import com.recipe.services.IRecipeService;

@Service
public class RecipeServiceImpl implements IRecipeService {

	
	@Autowired
	private IRecipeRepository recipeRepository;
	
	
	
	@Override
	public List<Recipe> getRecipesList() {
		List<Recipe> listOfRecipes = recipeRepository.findAll();
		return listOfRecipes;
		
		
	}
	
	@Override
	public Recipe addRecipe(Recipe recipe) {
		
		return recipeRepository.save(recipe);
		
	}

	public void updateRecipe(Recipe recipe)  {
		
		recipeRepository.save(recipe);
		
		
	}

	
	@Override
	public void deleteRecipe(int recipeId) {
		
		recipeRepository.deleteById(recipeId);
	}

	@Override
	public Recipe getRecipe(int recipeId) {
		
		return recipeRepository.findById(recipeId).get();
		
		
	}

	@Override
	public Optional<Recipe> getRecipeById(int recipeId) {
		
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		
		return recipe;
	}

	@Override
	public Optional<Recipe> getRecipeByName(String name) {
		
		Optional<Recipe> recipe = recipeRepository.findByName(name);
		
		return recipe;
	}

	
	
	

}
