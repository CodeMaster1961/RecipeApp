package com.example.recipeapp.data.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val recipes: List<Recipe>
)
