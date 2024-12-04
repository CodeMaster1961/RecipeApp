package com.example.recipeapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.ui.state.RecipeDetailsUiState
import com.example.recipeapp.ui.state.RecipeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    var recipeUiState: RecipeUiState by mutableStateOf(RecipeUiState.Loading)
    var recipeDetailsUiState: RecipeDetailsUiState by mutableStateOf(RecipeDetailsUiState.Loading)

    init {
        recipeList()
    }

    /**
     * Fetches a list of recipes from the repository and updates the UI state accordingly.
     * @author Ömer Aynaci
     *
     */
    private fun recipeList() {
        viewModelScope.launch {
            recipeUiState = RecipeUiState.Loading
            recipeUiState = try {
                RecipeUiState.Success(recipeRepository.getAllRecipes())
            } catch (error: HttpException) {
                RecipeUiState.Error
            }
        }
    }

    /**
     * Fetches a recipe by its ID from the repository and updates the UI state accordingly.
     * @author Ömer Aynaci
     * @param recipeId the id of a specific recipe
     */
    fun getRecipeById(recipeId: Int) {
        viewModelScope.launch {
            recipeDetailsUiState = RecipeDetailsUiState.Loading
            recipeDetailsUiState = try {
                RecipeDetailsUiState.Success(recipeRepository.getRecipeById(recipeId))
            } catch (error: HttpException) {
                RecipeDetailsUiState.Error
            }
        }
    }
}