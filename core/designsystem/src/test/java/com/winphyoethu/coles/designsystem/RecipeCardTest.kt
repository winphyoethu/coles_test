package com.winphyoethu.coles.designsystem

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.winphyoethu.coles.designsystem.colescomponent.RecipeCard
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.model.recipe.mockRecipe
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipeCardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkIfRecipeCardShowCorrectContent() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeCard(mockRecipe) {

                }
            }
        }

        composeTestRule.onNodeWithText(mockRecipe.dynamicTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRecipe.recipeDetail.cookingTime).assertIsDisplayed()
    }

}