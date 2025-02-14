package com.winphyoethu.coles.designsystem

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.winphyoethu.coles.designsystem.colescomponent.RecipeDetailCard
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.model.recipe.mockRecipeDetail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipeDetailCardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkIfRecipeCardShowCorrectContent() {
        composeTestRule.setContent {
            ColesTheme {
                RecipeDetailCard(mockRecipeDetail)
            }
        }

        composeTestRule.onNodeWithContentDescription("${mockRecipeDetail.amountLabel} ${mockRecipeDetail.amountNumber}").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("${mockRecipeDetail.cookingLabel} ${mockRecipeDetail.cookingTime}").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("${mockRecipeDetail.prepLabel} ${mockRecipeDetail.prepTime}").assertIsDisplayed()
    }

}