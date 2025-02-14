package com.winphyoethu.coles.recipe.screentest

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.features.recipe.R
import com.winphyoethu.coles.model.recipe.mockRecipe
import com.winphyoethu.coles.recipe.RecipeListScreen
import com.winphyoethu.coles.recipe.RecipeListUiState
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipeListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `RecipeList UiState - Show Recipe List show correct`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeListScreen(
                    RecipeListUiState.ShowRecipeList(persistentListOf(mockRecipe)),
                    onRecipeClick = {},
                    retryClick = {})
            }
        }

        composeTestRule.onNodeWithContentDescription(mockRecipe.dynamicThumbnailAlt)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRecipe.dynamicTitle).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("${mockRecipe.recipeDetail.cookingLabel} ${mockRecipe.recipeDetail.cookingTime}")
            .assertIsDisplayed()
    }

    @Test
    fun `RecipeDetail UiState - Recipe Error with Authentication Error`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeListScreen(
                    RecipeListUiState.RecipeListError(stringResId = R.string.authentication_error),
                    onRecipeClick = {},
                    retryClick = {})
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.resources.getString(R.string.authentication_error))
            .assertIsDisplayed()
    }

    @Test
    fun `RecipeDetail UiState - Recipe Error with Unknown Error`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeListScreen(
                    RecipeListUiState.RecipeListError(stringResId = R.string.unknown_error),
                    onRecipeClick = {},
                    retryClick = {})
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.resources.getString(R.string.unknown_error))
            .assertIsDisplayed()
    }

    @Test
    fun `RecipeDetail UiState - Recipe Error with Unexpected Error`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeListScreen(
                    RecipeListUiState.RecipeListError(stringResId = R.string.unexpected_error),
                    onRecipeClick = {},
                    retryClick = {})
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.resources.getString(R.string.unexpected_error))
            .assertIsDisplayed()
    }

}