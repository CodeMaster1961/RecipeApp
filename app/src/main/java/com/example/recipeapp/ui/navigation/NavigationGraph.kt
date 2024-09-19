package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.ui.RecipeListScreen
import com.example.recipeapp.ui.RecipeViewModel

@Composable
fun RecipeApp(
    viewModel: RecipeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "start_screen") {
        composable("start_screen") {
            RecipeListScreen(recipeUiState = viewModel.recipeUiState)
        }
    }
}