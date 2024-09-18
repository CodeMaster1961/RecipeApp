package com.example.recipeapp.data.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDto(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "recipes")
    val recipes: List<Recipe>,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "total")
    val total: Int
)