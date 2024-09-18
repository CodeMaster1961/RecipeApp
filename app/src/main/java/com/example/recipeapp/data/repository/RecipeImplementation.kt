package com.example.recipeapp.data.repository

import android.util.Log
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.data.remote.RecipeAPI
import retrofit2.Response
import timber.log.Timber

class RecipeImplementation(
    private val recipeAPI: RecipeAPI
) : RecipeRepository {

    override suspend fun getAllRecipes(): List<Recipe> {
        return try {
            val response = recipeAPI.getAllRecipes()
            response.body()?.recipes ?: emptyList()
        } catch (error: Exception) {
            Log.d("RecipeImplementation", "Error: ${error.message}")
            emptyList()
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return recipeAPI.getRecipeById(id)
    }
}