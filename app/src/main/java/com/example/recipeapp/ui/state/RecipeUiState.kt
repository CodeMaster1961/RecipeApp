package com.example.recipeapp.ui.state

import com.example.recipeapp.data.dtos.Recipe

sealed interface RecipeUiState {
    data class Success(val recipes: List<Recipe>): RecipeUiState
    object Error : RecipeUiState
    object Loading : RecipeUiState
}