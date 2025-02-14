package com.winphyoethu.coles.recipe.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winphyoethu.coles.model.recipe.Recipe
import com.winphyoethu.coles.recipe.RecipeListRoute
import kotlinx.serialization.Serializable

@Serializable
data object RecipeListScreen

fun NavController.navigateToRecipeList(navOptions: NavOptions? = null) {
    navigate(RecipeListScreen, navOptions)
}

fun NavGraphBuilder.recipeListScreen(
    onRecipeClick: (recipe: Recipe) -> Unit,
    onShowSnackbar: suspend (String?) -> Unit,
) {
    composable<RecipeListScreen> {
        RecipeListRoute(onRecipeClick, onShowSnackbar)
    }
}