package com.winphyoethu.coles.domain.recipe.repository

import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.model.recipe.Recipe
import kotlinx.collections.immutable.PersistentList

/**
 * API for Recipe Repository
 */
interface RecipeRepository {

    /**
     * Get Recipe
     *
     * @return ColesResult [ColesResult] which type is list of Recipe[Recipe]
     */
    suspend fun getRecipeList(): ColesResult<PersistentList<Recipe>>

    /**
     * Get Recipe by ID
     *
     * @return ColesResult [ColesResult] which type is Recipe[Recipe]
     */
    suspend fun getRecipeById(id: String): ColesResult<Recipe>

}