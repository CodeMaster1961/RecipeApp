package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp.ui.RecipeDetails
import com.example.recipeapp.ui.RecipeListScreen
import com.example.recipeapp.ui.RecipeViewModel

@Composable
fun RecipeApp(
    viewModel: RecipeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "start_screen") {
        composable("start_screen") {
            RecipeListScreen(recipeUiState = viewModel.recipeUiState, navController = navController)
        }

        composable(
            "recipeDetails/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: -1
            RecipeDetails(recipeId = recipeId, viewModel = viewModel)
        }
    }
}