package com.winphyoethu.coles.designsystem.colescomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.coles.designsystem.basiccomponent.ColesBody
import com.winphyoethu.coles.designsystem.basiccomponent.ColesSubTitle
import com.winphyoethu.coles.designsystem.ui.theme.ColesIcons
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.largeDp
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.designsystem.ui.theme.recipeDetailSize
import com.winphyoethu.coles.model.recipe.RecipeDetail
import com.winphyoethu.coles.model.recipe.mockRecipeDetail

/**
 * Recipe Detail Card to be used in Recipe Detail Screen
 */
@Composable
fun RecipeDetailCard(
    recipe: RecipeDetail,
    arrangement: Arrangement.Horizontal = Arrangement.Center
) {
    Row(horizontalArrangement = arrangement, modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(largeDp),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.semantics(mergeDescendants = true) {
                contentDescription = "${recipe.amountLabel} ${recipe.amountNumber}"
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(largeDp)
                    .width(recipeDetailSize)
            ) {
                Icon(imageVector = ColesIcons.serves, contentDescription = recipe.amountLabel)
                ColesSubTitle(subtitle = recipe.amountLabel)
                ColesBody(body = recipe.amountNumber.toString())
            }
        }
        Spacer(modifier = Modifier.width(mediumDp))
        Card(
            shape = RoundedCornerShape(largeDp),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.semantics(mergeDescendants = true) {
                contentDescription = "${recipe.cookingLabel} ${recipe.cookingTime}"
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(largeDp)
                    .width(recipeDetailSize)
            ) {
                Icon(imageVector = ColesIcons.timer, contentDescription = recipe.cookingLabel)
                ColesSubTitle(subtitle = recipe.cookingLabel)
                ColesBody(body = recipe.cookingTime)
            }
        }
        Spacer(modifier = Modifier.width(mediumDp))
        Card(
            shape = RoundedCornerShape(largeDp),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.semantics(mergeDescendants = true) {
                contentDescription = "${recipe.prepLabel} ${recipe.prepTime}"
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(largeDp)
                    .width(recipeDetailSize)
            ) {
                Icon(imageVector = ColesIcons.timer, contentDescription = recipe.prepLabel)
                ColesSubTitle(subtitle = recipe.prepLabel)
                ColesBody(body = recipe.prepTime)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeDetailCardPreview() {
    ColesTheme(dynamicColor = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            RecipeDetailCard(mockRecipeDetail)
        }
    }
}