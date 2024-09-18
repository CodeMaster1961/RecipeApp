package com.example.recipeapp.data.repository

import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.data.remote.RecipeAPI

class RecipeImplementation(
    private val recipeAPI: RecipeAPI
) : RecipeRepository {

    override suspend fun getAllRecipes(): List<Recipe> {
        return recipeAPI.getAllRecipes()
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return recipeAPI.getRecipeById(id)
    }
}