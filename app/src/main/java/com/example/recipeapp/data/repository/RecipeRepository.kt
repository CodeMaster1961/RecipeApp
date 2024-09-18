package com.example.recipeapp.data.repository

import com.example.recipeapp.data.dtos.Recipe

interface RecipeRepository {
    suspend fun getAllRecipes(): List<Recipe>

    suspend fun getRecipeById(id: Int): Recipe
}