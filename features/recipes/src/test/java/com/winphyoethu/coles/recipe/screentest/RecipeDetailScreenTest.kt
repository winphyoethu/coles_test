package com.winphyoethu.coles.recipe.screentest

import androidx.activity.ComponentActivity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.nhaarman.mockitokotlin2.mock
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.model.recipe.mockRecipe
import com.winphyoethu.coles.recipe.RecipeDetailScreen
import com.winphyoethu.coles.recipe.RecipeDetailUiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.winphyoethu.coles.features.recipe.R
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipeDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `RecipeDetail UiState - Show Recipe show correct`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeDetailScreen(
                    RecipeDetailUiState.ShowRecipe(mockRecipe),
                    retryClick = { },
                    backClick = { })
            }
        }

        composeTestRule.onNodeWithContentDescription(mockRecipe.dynamicThumbnailAlt)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRecipe.dynamicTitle).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("${mockRecipe.recipeDetail.cookingLabel} ${mockRecipe.recipeDetail.cookingTime}")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("${mockRecipe.recipeDetail.prepLabel} ${mockRecipe.recipeDetail.prepTime}")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("${mockRecipe.recipeDetail.amountLabel} ${mockRecipe.recipeDetail.amountNumber}")
            .assertIsDisplayed()
    }

    @Test
    fun `RecipeDetail UiState - Recipe Error with Authentication Error`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeDetailScreen(
                    RecipeDetailUiState.RecipeError(stringResId = R.string.authentication_error),
                    retryClick = { },
                    backClick = { })
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.resources.getString(R.string.authentication_error))
            .assertIsDisplayed()
    }

    @Test
    fun `RecipeDetail UiState - Recipe Error with Unknown Error`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeDetailScreen(
                    RecipeDetailUiState.RecipeError(stringResId = R.string.unknown_error),
                    retryClick = { },
                    backClick = { })
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.resources.getString(R.string.unknown_error))
            .assertIsDisplayed()
    }

    @Test
    fun `RecipeDetail UiState - Recipe Error with Unexpected Error`() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeDetailScreen(
                    RecipeDetailUiState.RecipeError(stringResId = R.string.unexpected_error),
                    retryClick = { },
                    backClick = { })
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.resources.getString(R.string.unexpected_error))
            .assertIsDisplayed()
    }

}