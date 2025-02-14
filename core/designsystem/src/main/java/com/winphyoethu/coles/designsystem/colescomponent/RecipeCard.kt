package com.winphyoethu.coles.designsystem.colescomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.winphyoethu.coles.designsystem.R
import com.winphyoethu.coles.designsystem.basiccomponent.ColesBody
import com.winphyoethu.coles.designsystem.basiccomponent.ColesSubTitle
import com.winphyoethu.coles.designsystem.ui.theme.ColesIcons
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.largeDp
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.designsystem.ui.theme.recipeImageSize
import com.winphyoethu.coles.model.recipe.Recipe
import com.winphyoethu.coles.model.recipe.mockRecipe

/**
 * Recipe Card to be used in Recipe List Screen
 */
@Composable
fun RecipeCard(recipe: Recipe, onClick: (recipe: Recipe) -> Unit) {
    val timerIcon = remember { ColesIcons.timer }
    Card(
        shape = RoundedCornerShape(largeDp),
        modifier = Modifier
            .padding(mediumDp)
            .height(recipeImageSize),
        onClick = { onClick(recipe) }
    ) {
        Box {
            AsyncImage(
                model = recipe.dynamicThumbnail,
                contentDescription = recipe.dynamicThumbnailAlt,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .padding(mediumDp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(largeDp)
                    )
                    .padding(mediumDp)
                    .semantics(mergeDescendants = true) {
                        contentDescription =
                            "${recipe.recipeDetail.cookingLabel} ${recipe.recipeDetail.cookingTime}"
                    },
                horizontalArrangement = Arrangement.spacedBy(mediumDp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = timerIcon,
                    contentDescription = null, // decoration
                    modifier = Modifier.size(largeDp),
                    tint = Color.Black
                )
                ColesBody(body = recipe.recipeDetail.cookingTime, color = Color.Black)
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                        )
                    )
                    .fillMaxWidth()
                    .padding(largeDp)
            ) {
                ColesSubTitle(subtitle = recipe.dynamicTitle, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
private fun RecipeCardPreview() {
    ColesTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            RecipeCard(recipe = mockRecipe, onClick = {

            })
        }
    }
}