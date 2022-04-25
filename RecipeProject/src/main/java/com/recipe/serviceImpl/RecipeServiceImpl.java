package com.recipe.serviceImpl;

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
	public List<Recipe> recipesList() {
		
		return recipeRepository.findAll();
		
		
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

	
	
	

}
