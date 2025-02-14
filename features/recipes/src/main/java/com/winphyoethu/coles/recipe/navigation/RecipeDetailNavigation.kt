package com.winphyoethu.coles.recipe.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.winphyoethu.coles.recipe.RecipeDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailScreen(val recipeId: String)

fun NavController.navigateToRecipeDetail(recipeId: String, navOptions: NavOptions? = null) {
    navigate(route = RecipeDetailScreen(recipeId), navOptions)
}

fun NavGraphBuilder.recipeDetailScreen(
    backClick: () -> Unit,
    onShowSnackbar: suspend (String?) -> Unit,
) {
    composable<RecipeDetailScreen> { backStack ->
        val recipeRoute = backStack.toRoute<RecipeDetailScreen>()
        RecipeDetailRoute(
            recipeRoute.recipeId,
            backClick = backClick,
            onShowSnackbar = onShowSnackbar
        )
    }
}