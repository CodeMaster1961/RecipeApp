package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp.ui.RecipeDetailsScreen
import com.example.recipeapp.ui.RecipeInstructionsScreen
import com.example.recipeapp.ui.RecipeListScreen
import com.example.recipeapp.ui.RecipeViewModel

@Composable
fun RecipeApp(
    viewModel: RecipeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screens.START_SCREEN.name) {
        composable(Screens.START_SCREEN.name) {
            RecipeListScreen(recipeUiState = viewModel.recipeUiState, navController = navController)
        }

        composable(
            "recipeDetails/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: -1
            RecipeDetailsScreen(recipeId = recipeId, viewModel = viewModel, navigateBack = {
                navController.navigateUp()
            }, navigateToInstructions = {
                navController.navigate("recipeInstructions/$recipeId")
            })
        }

        composable("recipeInstructions/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: -1
            RecipeInstructionsScreen(recipeId = recipeId, viewModel = viewModel, navigateBack = {
                navController.navigateUp()
            })
        }
    }
}