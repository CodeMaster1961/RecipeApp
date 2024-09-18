package com.example.recipeapp.data.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("limit")
    val limit: Int,
    @SerialName("recipes")
    val recipes: List<Recipe>,
    @SerialName("skip")
    val skip: Int,
    @SerialName("total")
    val total: Int
)