package com.example.recipeapp.di

import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.ui.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RecipeViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideRecipeViewModel(recipeRepository: RecipeRepository): RecipeViewModel {
        return RecipeViewModel(recipeRepository)
    }

}