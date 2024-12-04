package com.example.recipeapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.ui.state.RecipeDetailsUiState

/**
 * Displays the recipe details screen
 * @param recipeId the id of the recipe
 * @param viewModel the view model of the recipe
 * @param navigateBack action to navigate back to previous screen
 * @param navigateToInstructions action to navigate to instructions screen
 */
@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    viewModel: RecipeViewModel,
    navigateBack: () -> Unit,
    navigateToInstructions: () -> Unit
) {
    val recipeUiState = viewModel.recipeDetailsUiState

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    when (recipeUiState) {
        is RecipeDetailsUiState.Success -> RecipeDetailsSection(
            recipe = recipeUiState.recipe,
            navigateBack = navigateBack,
            navigateToInstructions = navigateToInstructions
        )

        is RecipeDetailsUiState.Error -> Text(text = stringResource(R.string.error_message))
        is RecipeDetailsUiState.Loading -> Text(text = stringResource(R.string.loading_message))
    }
}

/**
 * Displays the details of a recipe
 * @author Ömer Aynaci
 * @param recipe the recipe
 * @param navigateBack action to navigate back to previous screen
 * @param navigateToInstructions action to navigate to instructions screen
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeDetailsSection(
    recipe: Recipe,
    navigateBack: () -> Unit,
    navigateToInstructions: () -> Unit
) {
    Scaffold(
        topBar = {
            RecipeDetailTopAppBar(recipeTitle = recipe.name, navigateBack = navigateBack)
        }
    ) {
        RecipeDetailsImage(
            imageUrl = recipe.image
        )
        IngredientsTitle()
        IngredientsOverview(
            ingredients = recipe.ingredients,
        )
        InstructionsButton(navigateToInstructions = navigateToInstructions)
    }
}

/**
 * Displays the button to navigate to the instructions screen
 * @author Ömer Aynaci
 * @param navigateToInstructions action to navigate to instructions screen
 */
@Composable
fun InstructionsButton(navigateToInstructions: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 160.dp, end = 25.dp)
    ) {
        Button(
            onClick = { navigateToInstructions() },
            modifier = Modifier.size(160.dp, 45.dp)
        ) {
            Text(text = stringResource(R.string.instructions_title))
        }
    }
}

/**
 * Displays the title of the ingredients
 * @author Ömer Aynaci
 */
@Composable
fun IngredientsTitle() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp, start = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.ingredients_title),
            fontSize = 26.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Black
        )
    }
}

/**
 * Displays the image of a specific recipe
 * @author Ömer Aynaci
 * @param imageUrl the url of the image
 * @param modifier the modifier of the image
 */
@Composable
fun RecipeDetailsImage(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.recipe_image_description),
        modifier = modifier
            .padding(top = 100.dp, start = 10.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

/**
 * Displays the top app bar of the recipe details screen
 * @author Ömer Aynaci
 * @param recipeTitle the recipe title
 * @param navigateBack action to go back to the previous screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailTopAppBar(recipeTitle: String, navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = recipeTitle, fontSize = 26.sp, fontWeight = FontWeight.Black)
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(
                        R.string.back_content_description
                    )
                )
            }
        })
}

/**
 * Displays a list of ingredients of a recipe
 * @author Ömer Aynaci
 * @param ingredients a list of ingredients of a recipe
 */
@Composable
fun IngredientsOverview(ingredients: List<String>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(19.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(top = 510.dp))
    ) {
        items(ingredients) { ingredient ->
            Text(text = "• $ingredient", fontSize = 19.sp, textAlign = TextAlign.Center)
        }
    }
}


