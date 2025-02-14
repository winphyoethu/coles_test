package com.winphyoethu.coles.designsystem

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.winphyoethu.coles.designsystem.colescomponent.RecipeIngredient
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipeIngredientTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkIfRecipeCardShowCorrectContent() {
        val ingredient = "1 egg"
        composeTestRule.setContent {
            ColesTheme {
                RecipeIngredient(ingredient)
            }
        }

        composeTestRule.onNodeWithText(ingredient).assertIsDisplayed()
    }

}