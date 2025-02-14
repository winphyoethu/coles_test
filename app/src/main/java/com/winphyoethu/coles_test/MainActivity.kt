package com.winphyoethu.coles_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.winphyoethu.coles.recipe.navigation.RecipeListScreen
import com.winphyoethu.coles.recipe.navigation.navigateToRecipeDetail
import com.winphyoethu.coles.recipe.navigation.recipeDetailScreen
import com.winphyoethu.coles.recipe.navigation.recipeListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()

            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) { paddingValues ->
                NavHost(
                    navController,
                    startDestination = RecipeListScreen,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    recipeListScreen(onRecipeClick = { recipe ->
                        navController.navigateToRecipeDetail(recipe.dynamicTitle)
                    }, onShowSnackbar = { message ->
                        snackbarHostState.showSnackbar(message = message ?: "")
                    })
                    recipeDetailScreen(backClick = {
                        navController.popBackStack()
                    }, onShowSnackbar = { message ->
                        snackbarHostState.showSnackbar(message = message ?: "")
                    })
                }
            }

        }
    }
}