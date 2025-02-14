package com.winphyoethu.coles.designsystem.colescomponent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.coles.designsystem.basiccomponent.ColesBody
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.designsystem.ui.theme.smallDp

/**
 * Recipe Ingredient to be used in Recipe Detail Screen
 */
@Composable
fun RecipeIngredient(ingredient: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumDp)
            .semantics(mergeDescendants = true) {
                contentDescription = ingredient
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(mediumDp)
    ) {
        Canvas(
            modifier = Modifier
                .size(smallDp)
        ) {
            drawCircle(color = Color.Red)
        }
        ColesBody(body = ingredient)
    }
}

@Preview
@Composable
private fun RecipeIngredientPreview() {
    ColesTheme {
        Surface {
            RecipeIngredient("1 egg")
        }
    }
}