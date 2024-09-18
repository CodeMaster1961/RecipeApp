package com.example.recipeapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.ui.state.RecipeUiState

@Composable
fun RecipeListScreen(
    recipeUiState: RecipeUiState
) {
    when (recipeUiState) {
        is RecipeUiState.Success -> RecipeList(recipes = recipeUiState.recipes)
        is RecipeUiState.Loading -> LoadingIndicator()
        is RecipeUiState.Error -> Text(text = "Error")
    }
}

@Composable
fun RecipeApp(
    viewModel: RecipeViewModel
) {
    RecipeListScreen(recipeUiState = viewModel.recipeUiState)
}

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn(verticalArrangement = Arrangement.Center) {
        items(recipes) { recipe ->
            AsyncImage(model = recipe.image, contentDescription = "image")
            Text(text = recipe.name, fontSize = 26.sp)
        }
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}