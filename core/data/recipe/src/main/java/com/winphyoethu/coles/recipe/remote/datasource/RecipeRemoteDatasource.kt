package com.winphyoethu.coles.recipe.remote.datasource

import com.winphyoethu.coles.recipe.remote.apiservice.RecipeService
import com.winphyoethu.coles.recipe.remote.model.RemoteRecipe
import com.winphyoethu.coles.recipe.remote.model.RemoteRecipeList
import retrofit2.Response
import javax.inject.Inject

/**
 * API for RacingRemoteDatasource
 *
 * @see RecipeRemoteDatasourceImpl for actual implementation
 *
 */
internal interface RecipeRemoteDatasource {

    /**
     * Get recipe
     */
    suspend fun getRecipe(): Response<RemoteRecipeList>

}

/**
 * Implementation of RecipeRemoteDatasource [RecipeRemoteDatasource]
 *
 * @param recipeService Retrofit Recipe Api [RecipeService]
 */
class RecipeRemoteDatasourceImpl @Inject constructor(val recipeService: RecipeService) :
    RecipeRemoteDatasource {

    override suspend fun getRecipe(): Response<RemoteRecipeList> {
        return recipeService.getRecipe()
    }

}