package com.example.recipeapp.di

import com.example.recipeapp.data.remote.RecipeAPI
import com.example.recipeapp.data.remote.request.retrofitInstance
import com.example.recipeapp.data.repository.RecipeImplementation
import com.example.recipeapp.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeAppModule {

    @Singleton
    @Provides
    fun provideRecipeAPI(): RecipeAPI {
        return retrofitInstance().create(RecipeAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeRepository(recipeAPI: RecipeAPI): RecipeRepository {
        return RecipeImplementation(recipeAPI)
    }
}