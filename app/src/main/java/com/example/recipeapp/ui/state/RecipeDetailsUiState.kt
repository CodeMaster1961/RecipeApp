package com.example.recipeapp.ui.state

import com.example.recipeapp.data.dtos.Recipe

sealed interface RecipeDetailsUiState {
    data class Success(val recipe: Recipe) : RecipeDetailsUiState
    object Error : RecipeDetailsUiState
    object Loading : RecipeDetailsUiState
}