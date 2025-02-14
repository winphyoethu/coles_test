package com.winphyoethu.coles.recipe.remote.model

import com.squareup.moshi.Json

/**
 * Recipe List from remote datasource
 */
data class RemoteRecipeList(
    @Json(name = "recipes")
    val recipeList: List<RemoteRecipe>
)

/**
 * Recipe from remote datasource
 */
data class RemoteRecipe(
    @Json(name = "dynamicTitle")
    val dynamicTitle: String,
    @Json(name = "dynamicDescription")
    val dynamicDescription: String,
    @Json(name = "dynamicThumbnail")
    val dynamicThumbnail: String,
    @Json(name = "dynamicThumbnailAlt")
    val dynamicThumbnailAlt: String,
    @Json(name = "recipeDetails")
    val recipeDetail: RemoteRecipeDetail,
    @Json(name = "ingredients")
    val ingredients: List<RemoteIngredient>
)

/**
 * Recipe detail from remote datasource
 */
data class RemoteRecipeDetail(
    @Json(name = "amountLabel")
    val amountLabel: String,
    @Json(name = "amountNumber")
    val amountNumber: Int,
    @Json(name = "prepLabel")
    val prepLabel: String,
    @Json(name = "prepTime")
    val prepTime: String,
    @Json(name = "prepNote")
    val prepNote: String?,
    @Json(name = "cookingLabel")
    val cookingLabel: String,
    @Json(name = "cookingTime")
    val cookingTime: String
)

data class RemoteIngredient(
    @Json(name = "ingredient")
    val ingredient: String
)

/**
 * Mocked Remote Recipe Detail to be used in unit test
 */
val mockRemoteRecipeList = RemoteRecipeList(
    recipeList = listOf(mockRemoteRecipe)
)

/**
 * Mocked Remote Recipe Detail to be used in unit test
 */
val mockRemoteRecipeDetail: RemoteRecipeDetail
    get() = RemoteRecipeDetail(
        amountLabel = "Serves",
        amountNumber = 8,
        prepLabel = "Prep",
        prepTime = "15m",
        prepNote = "+ cooling time",
        cookingLabel = "Cooking",
        cookingTime = "15m"
    )

/**
 * Mocked Remote Recipe Ingredient to be used in unit test
 */
val mockRemoteIngredient = RemoteIngredient(
    ingredient = "1 egg"
)

/**
 * Mocked Remote Recipe to be used in unit test
 */
val mockRemoteRecipe: RemoteRecipe
    get() = RemoteRecipe(
        dynamicTitle = "Curtis Stone's tomato and bread salad with BBQ eggplant and capsicum",
        dynamicDescription = "For a crowd-pleasing salad, try this tasty combination of fresh tomato, crunchy bread and BBQ veggies. It’s topped with fresh basil and oregano for a finishing touch.",
        dynamicThumbnail = "/content/dam/coles/inspire-create/thumbnails/Tomato-and-bread-salad-480x288.jpg",
        dynamicThumbnailAlt = "Tomato, bread and eggplant salad served in a large plate topped with basil leaves with vinaigrette on the side",
        recipeDetail = mockRemoteRecipeDetail,
        ingredients = listOf(
            RemoteIngredient(ingredient = "1 cup (250ml) extra virgin olive oil, divided"),
            RemoteIngredient(ingredient = "4 cups (240g) 2cm-pieces day-old Coles Bakery Stone Baked by Laurent Pane Di Casa"),
            RemoteIngredient(ingredient = "4 Lebanese eggplants, halved lengthways"),
            RemoteIngredient(ingredient = "1 red capsicum, quartered, seeded"),
            RemoteIngredient(ingredient = "1 yellow capsicum, quartered, seeded"),
            RemoteIngredient(ingredient = "1/2 cup (125ml) red wine vinegar"),
            RemoteIngredient(ingredient = "1 tbs red wine vinegar, extra"),
            RemoteIngredient(ingredient = "3 garlic cloves, crushed"),
            RemoteIngredient(ingredient = "4 medium vine-ripened tomatoes, cut into 3cm pieces"),
            RemoteIngredient(ingredient = "200g grape kumato tomatoes, halved"),
            RemoteIngredient(ingredient = "1 Lebanese cucumber, cut into 3cm pieces"),
            RemoteIngredient(ingredient = "1 long red chilli, seeded, finely chopped"),
            RemoteIngredient(ingredient = "2/3 cup basil leaves"),
            RemoteIngredient(ingredient = "2 tsp oregano sprigs"),
        )
    )