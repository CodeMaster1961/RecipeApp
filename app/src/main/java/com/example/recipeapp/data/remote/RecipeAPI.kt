package com.example.recipeapp.data.remote

import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.data.dtos.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeAPI {

    @GET("recipes?limit=50")
    suspend fun getAllRecipes(): Response<RecipeResponse>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe
}