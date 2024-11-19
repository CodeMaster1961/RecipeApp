package com.example.recipeapp.data.repository

import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.data.remote.RecipeAPI
import timber.log.Timber

class RecipeImplementation(
    private val recipeAPI: RecipeAPI
) : RecipeRepository {

    /**
     * Fetches a list of recipes
     * @author Ömer Aynaci
     * @return a list of recipes
     */
    override suspend fun getAllRecipes(): List<Recipe> {
        return try {
            val response = recipeAPI.getAllRecipes()
            response.body()?.recipes ?: emptyList()
        } catch (error: Exception) {
            Timber.tag("RecipeImplementation").d("Error: %s", error.message)
            emptyList()
        }
    }

    /**
     * Gets a recipe by its id
     * @author Ömer Aynaci
     * @param id the id of the recipe
     * @return an instance of recipe
     */
    override suspend fun getRecipeById(id: Int): Recipe {
        return recipeAPI.getRecipeById(id)
    }
}