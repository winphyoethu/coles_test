package com.winphyoethu.coles.model.recipe

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Recipe to be used in View
 */
@Stable
data class Recipe(
    val dynamicTitle: String,
    val dynamicDescription: String,
    val dynamicThumbnail: String,
    val dynamicThumbnailAlt: String,
    val recipeDetail: RecipeDetail,
    val ingredientList: PersistentList<String>
)

/**
 * Recipe detail to be used in View
 */
@Stable
data class RecipeDetail(
    val amountLabel: String,
    val amountNumber: Int,
    val prepLabel: String,
    val prepTime: String,
    val prepNote: String,
    val cookingLabel: String,
    val cookingTime: String
)

/**
 * Mocked Recipe to be used in compose preview and test
 */
val mockRecipe: Recipe
    get() = Recipe(
        dynamicTitle = "Curtis Stone's tomato and bread salad with BBQ eggplant and capsicum",
        dynamicDescription = "For a crowd-pleasing salad, try this tasty combination of fresh tomato, crunchy bread and BBQ veggies. It’s topped with fresh basil and oregano for a finishing touch.",
        dynamicThumbnail = "/content/dam/coles/inspire-create/thumbnails/Tomato-and-bread-salad-480x288.jpg",
        dynamicThumbnailAlt = "Tomato, bread and eggplant salad served in a large plate topped with basil leaves with vinaigrette on the side",
        recipeDetail = mockRecipeDetail,
        ingredientList = persistentListOf(
            "1 cup (250ml) extra virgin olive oil, divided",
            "4 cups (240g) 2cm-pieces day-old Coles Bakery Stone Baked by Laurent Pane Di Casa",
            "4 Lebanese eggplants, halved lengthways",
            "1 red capsicum, quartered, seeded",
            "1 yellow capsicum, quartered, seeded",
            "1/2 cup (125ml) red wine vinegar",
            "1 tbs red wine vinegar, extra",
            "3 garlic cloves, crushed",
            "4 medium vine-ripened tomatoes, cut into 3cm pieces",
            "200g grape kumato tomatoes, halved",
            "1 Lebanese cucumber, cut into 3cm pieces",
            "1 long red chilli, seeded, finely chopped",
            "2/3 cup basil leaves",
            "2 tsp oregano sprigs"
        )
    )

/**
 * Mocked Recipe Detail to be used in compose preview and test
 */
val mockRecipeDetail = RecipeDetail(
    amountLabel = "Serves",
    amountNumber = 8,
    prepLabel = "Prep",
    prepTime = "15m",
    prepNote = "+ cooling time",
    cookingLabel = "Cooking",
    cookingTime = "15m"
)