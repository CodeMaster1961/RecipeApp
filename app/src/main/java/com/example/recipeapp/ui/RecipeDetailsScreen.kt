package com.example.recipeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.ui.state.RecipeDetailsUiState


@Composable
fun RecipeDetails(recipeId: Int, viewModel: RecipeViewModel) {
    val recipeUiState = viewModel.recipeDetailsUiState

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    when (recipeUiState) {
        is RecipeDetailsUiState.Success -> RecipeDetailsScreen(recipe = recipeUiState.recipe)
        is RecipeDetailsUiState.Error -> Text(text = "Error")
        is RecipeDetailsUiState.Loading -> Text(text = "Loading")
    }
}

@Composable
fun RecipeDetailsScreen(recipe: Recipe) {
    Column {
        Text(text = recipe.name, fontSize = 24.sp)
        Text(text = recipe.cuisine, fontSize = 16.sp)
        LoadAllIngredients(ingredients = recipe.ingredients)
    }
}

@Composable
fun LoadAllIngredients(ingredients: List<String>) {
    LazyColumn {
        items(ingredients) { ingredient ->
            Text(text = ingredient)
        }
    }
}