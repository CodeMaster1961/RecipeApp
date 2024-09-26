package com.example.recipeapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.ui.state.RecipeDetailsUiState


@Composable
fun RecipeDetails(
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
        is RecipeDetailsUiState.Success -> RecipeDetailsScreen(
            recipe = recipeUiState.recipe,
            navigateBack = navigateBack,
            navigateToInstructions = navigateToInstructions
        )

        is RecipeDetailsUiState.Error -> Text(text = "Error")
        is RecipeDetailsUiState.Loading -> Text(text = "Loading")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeDetailsScreen(
    recipe: Recipe,
    navigateBack: () -> Unit,
    navigateToInstructions: () -> Unit
) {
    Scaffold(
        topBar = {
            RecipeDetailTopAppBar(recipeTitle = recipe.name, navigateBack = navigateBack)
        }
    ) {
        RecipeNewImage(
            imageUrl = recipe.image
        )
        IngredientsTitle()
        IngredientsOverview(
            ingredients = recipe.ingredients,
        )
        InstructionsButton(navigateToInstructions = navigateToInstructions)
    }
}

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
            modifier = Modifier.size(140.dp, 45.dp)
        ) {
            Text(text = "Instructions")
        }
    }
}

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
            text = "Ingredients",
            fontSize = 26.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun RecipeNewImage(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "recipe image",
        modifier = modifier
            .padding(top = 100.dp, start = 10.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailTopAppBar(recipeTitle: String, navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = recipeTitle, fontSize = 26.sp, fontWeight = FontWeight.Black)
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        })
}

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


