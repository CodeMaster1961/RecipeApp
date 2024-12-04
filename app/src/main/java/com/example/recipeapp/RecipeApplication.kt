package com.example.recipeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * The application class for the recipe app
 * @author Ömer Aynaci
 */
@HiltAndroidApp
class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}