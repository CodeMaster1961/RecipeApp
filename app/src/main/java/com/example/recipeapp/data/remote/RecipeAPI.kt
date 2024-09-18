package com.example.recipeapp.data.remote

import com.example.recipeapp.data.dtos.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeAPI {

    @GET("recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe
}