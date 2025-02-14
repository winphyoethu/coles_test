package com.winphyoethu.coles.recipe

import Dispatcher
import android.util.Log
import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.model.recipe.Recipe
import com.winphyoethu.coles.model.recipe.RecipeDetail
import com.winphyoethu.coles.network.BuildConfig
import com.winphyoethu.coles.recipe.remote.datasource.RecipeRemoteDatasource
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of RecipeRepository [RecipeRepository]
 *
 * @param remoteDatasource Remote Data Source of recipe [RecipeRemoteDatasource]
 * @param io Coroutine IO Dispatcher
 */
internal class RecipeRepositoryImpl @Inject constructor(
    val remoteDatasource: RecipeRemoteDatasource,
    @Dispatcher(ColesDispatchers.IO) val io: CoroutineDispatcher
) : RecipeRepository {

    override suspend fun getRecipeList(): ColesResult<PersistentList<Recipe>> {
        return withContext(io) {
            try {
                val response = remoteDatasource.getRecipe()

                if (response.isSuccessful && response.code() == 200) {
                    val result = response.body()!!.recipeList.map {
                        Recipe(
                            dynamicTitle = it.dynamicTitle,
                            dynamicDescription = it.dynamicDescription,
                            dynamicThumbnail = BuildConfig.BASE_URL + it.dynamicThumbnail,
                            dynamicThumbnailAlt = it.dynamicThumbnailAlt,
                            recipeDetail = RecipeDetail(
                                amountLabel = it.recipeDetail.amountLabel,
                                amountNumber = it.recipeDetail.amountNumber,
                                prepLabel = it.recipeDetail.prepLabel,
                                prepTime = it.recipeDetail.prepTime,
                                prepNote = it.recipeDetail.prepNote ?: "",
                                cookingLabel = it.recipeDetail.cookingLabel,
                                cookingTime = it.recipeDetail.cookingTime
                            ),
                            ingredientList = it.ingredients.map { ingredient -> ingredient.ingredient }
                                .toPersistentList()
                        )
                    }.toPersistentList()
                    ColesResult.Success(result)
                } else {
                    when (response.code()) {
                        401 -> ColesResult.Error(RecipeErrorCode.AuthenticationError)
                        else -> ColesResult.Error(RecipeErrorCode.UnknownError)
                    }
                }
            } catch (e: Exception) {
                ColesResult.Error(RecipeErrorCode.UnexpectedError)
            }
        }
    }

    override suspend fun getRecipeById(id: String): ColesResult<Recipe> {
        return withContext(io) {
            try {
                val response = remoteDatasource.getRecipe()

                if (response.isSuccessful && response.code() == 200) {
                    val result = response.body()!!.recipeList.filter { it.dynamicTitle == id }.map {
                        Recipe(
                            dynamicTitle = it.dynamicTitle,
                            dynamicDescription = it.dynamicDescription,
                            dynamicThumbnail = BuildConfig.BASE_URL + it.dynamicThumbnail,
                            dynamicThumbnailAlt = it.dynamicThumbnailAlt,
                            recipeDetail = RecipeDetail(
                                amountLabel = it.recipeDetail.amountLabel,
                                amountNumber = it.recipeDetail.amountNumber,
                                prepLabel = it.recipeDetail.prepLabel,
                                prepTime = it.recipeDetail.prepTime,
                                prepNote = it.recipeDetail.prepNote ?: "",
                                cookingLabel = it.recipeDetail.cookingLabel,
                                cookingTime = it.recipeDetail.cookingTime
                            ),
                            ingredientList = it.ingredients.map { ingredient -> ingredient.ingredient }
                                .toPersistentList()
                        )
                    }.first()
                    ColesResult.Success(result)
                } else {
                    when (response.code()) {
                        401 -> ColesResult.Error(RecipeErrorCode.AuthenticationError)
                        else -> ColesResult.Error(RecipeErrorCode.UnknownError)
                    }
                }
            } catch (e: Exception) {
                ColesResult.Error(RecipeErrorCode.UnexpectedError)
            }
        }
    }

}