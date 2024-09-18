package com.example.recipeapp.data.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    @SerialName("caloriesPerServing")
    val caloriesPerServing: Int,
    @SerialName("cookTimeMinutes")
    val cookTimeMinutes: Int,
    @SerialName("cuisine")
    val cuisine: String,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("instructions")
    val instructions: List<String>,
    @SerialName("mealType")
    val mealType: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("prepTimeMinutes")
    val prepTimeMinutes: Int,
    @SerialName("rating")
    val rating: Double,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("servings")
    val servings: Int,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("userId")
    val userId: Int
)