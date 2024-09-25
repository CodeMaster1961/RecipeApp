package com.example.recipeapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.ui.state.RecipeDetailsUiState


@Composable
fun RecipeDetails(recipeId: Int, viewModel: RecipeViewModel, navigateBack: () -> Unit) {
    val recipeUiState = viewModel.recipeDetailsUiState

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    when (recipeUiState) {
        is RecipeDetailsUiState.Success -> RecipeDetailsScreen(
            recipe = recipeUiState.recipe,
            navigateBack = navigateBack
        )

        is RecipeDetailsUiState.Error -> Text(text = "Error")
        is RecipeDetailsUiState.Loading -> Text(text = "Loading")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeDetailsScreen(recipe: Recipe, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            RecipeDetailTopAppBar(recipeTitle = recipe.name, navigateBack = navigateBack)
        }
    ) {
        LoadAllIngredients(ingredients = recipe.ingredients)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailTopAppBar(recipeTitle: String, navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = recipeTitle)
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        })
}

@Composable
fun LoadAllIngredients(ingredients: List<String>) {
    LazyColumn {
        items(ingredients) { ingredient ->
            Text(text = ingredient)
        }
    }
}