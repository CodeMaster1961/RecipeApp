package com.example.recipeapp.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.recipeapp.ui.state.RecipeDetailsUiState

@Composable
fun RecipeInstructionsScreen(recipeId: Int,viewModel: RecipeViewModel) {
    val recipeState = viewModel.recipeDetailsUiState

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    when(recipeState) {
        is RecipeDetailsUiState.Success -> InstructionsOverview(instructions = recipeState.recipe.instructions)
        is RecipeDetailsUiState.Error -> Text(text = "Error")
        is RecipeDetailsUiState.Loading -> Text(text = "Loading")

    }
}