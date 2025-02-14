package com.winphyoethu.coles.recipe.remote.apiservice

import com.winphyoethu.coles.recipe.remote.model.RemoteRecipeList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Recipe Api Service
 */
interface RecipeService {

    @GET("get_recipe")
    suspend fun getRecipe(): Response<RemoteRecipeList>

    @GET("get_recipe_by_id")
    suspend fun getRecipeById(): Response<RemoteRecipeList>

}