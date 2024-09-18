package com.example.recipeapp.data.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Recipe(
    @Json(name = "caloriesPerServing")
    val caloriesPerServing: Int,
    @Json(name = "cookTimeMinutes")
    val cookTimeMinutes: Int,
    @Json(name = "cuisine")
    val cuisine: String,
    @Json(name = "difficulty")
    val difficulty: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "ingredients")
    val ingredients: List<String>,
    @Json(name = "instructions")
    val instructions: List<String>,
    @Json(name = "mealType")
    val mealType: List<String>,
    @Json(name = "name")
    val name: String,
    @Json(name = "prepTimeMinutes")
    val prepTimeMinutes: Int,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "reviewCount")
    val reviewCount: Int,
    @Json(name = "servings")
    val servings: Int,
    @Json(name = "tags")
    val tags: List<String>,
    @Json(name = "userId")
    val userId: Int
)