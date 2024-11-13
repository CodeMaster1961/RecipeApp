package com.example.recipeapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.ui.state.RecipeDetailsUiState

/**
 * The screen for displaying instructions of a recipe
 * @author Ömer Aynaci
 * @param recipeId the id of a specific recipe
 * @param viewModel the view model of the recipe
 * @param navigateBack action to navigate back to the previous screen
 */
@Composable
fun RecipeInstructionsScreen(recipeId: Int, viewModel: RecipeViewModel, navigateBack: () -> Unit) {
    val recipeState = viewModel.recipeDetailsUiState

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    when (recipeState) {
        is RecipeDetailsUiState.Success -> InstructionSection(
            instructions = recipeState.recipe.instructions,
            navigateBack = navigateBack
        )

        is RecipeDetailsUiState.Error -> Text(text = "Error")
        is RecipeDetailsUiState.Loading -> Text(text = "Loading")

    }
}

/**
 * A section for instructions of a recipe
 * @author Ömer Aynaci
 * @param instructions a list of instructions of a recipe
 * @param navigateBack button to navigate back to the previous screen
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InstructionSection(instructions: List<String>, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            RecipeDetailTopAppBar(
                recipeTitle = stringResource(id = R.string.instructions_title),
                navigateBack = navigateBack
            )
        }
    ) {
        InstructionsOverview(instructions = instructions)
    }
}

/**
 * Displays the instructions of a recipe
 * @author Ömer Aynaci
 * @param instructions a list of instructions of a recipe
 */
@Composable
fun InstructionsOverview(instructions: List<String>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(top = 100.dp))
    ) {
        instructions.forEachIndexed { index, instruction ->
            item {
                Text(
                    text = "${1 + index}. $instruction",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}