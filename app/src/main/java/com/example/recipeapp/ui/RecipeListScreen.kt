package com.example.recipeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.data.dtos.Recipe
import com.example.recipeapp.ui.state.RecipeUiState

@Composable
fun RecipeListScreen(
    recipeUiState: RecipeUiState,
    navController: NavHostController
) {
    when (recipeUiState) {
        is RecipeUiState.Success -> RecipeList(
            recipes = recipeUiState.recipes,
            onClick = { recipeId ->
                navController.navigate("recipeDetails/$recipeId")
            })

        is RecipeUiState.Loading -> LoadingIndicator()
        is RecipeUiState.Error -> Text(text = stringResource(R.string.error_message))
    }
}


@Composable
fun RecipeList(recipes: List<Recipe>, onClick: (Int) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.Center) {
        items(recipes) { recipe ->
            RecipeCard(recipe = recipe, onClick = onClick)
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


@Composable
fun RecipeCard(recipe: Recipe, onClick: (Int) -> Unit) {
    Card(
        onClick = { onClick(recipe.id) }, shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5DC)
        ),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .width(180.dp)
            .padding(end = 10.dp, start = 10.dp, top = 10.dp, bottom = 20.dp)
    ) {
        Box {
            RecipeContent(
                recipe = recipe, modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            RatingIcon(
                rating = recipe.rating,
                icon = Icons.Filled.Star,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 10.dp, top = 10.dp)
                    .wrapContentSize()
            )
        }
    }
}

@Composable
fun RecipeContent(recipe: Recipe, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        RecipeImage(imageUrl = recipe.image)
        Spacer(modifier = Modifier.height(80.dp))
        RecipeTitle(
            title = recipe.name, modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
    }
}

@Composable
fun RatingIcon(rating: Double, icon: ImageVector, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.review_star_description),
            tint = Color.Yellow,
            modifier = Modifier.size(50.dp)
        )
        RecipeRatingCount(rating = rating)
    }
}

@Composable
fun RecipeImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.image_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun RecipeTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun RecipeRatingCount(rating: Double) {
    Column {
        Text(
            text = rating.toString(),
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            color = Color.Blue,
            modifier = Modifier.padding(top = 10.dp, start = 8.dp)
        )
    }
}