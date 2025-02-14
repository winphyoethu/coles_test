package com.winphyoethu.coles.domain.recipe.errorcode

import com.winphyoethu.coles.common.result.ErrorCode

/**
 * Error Codes for Recipe
 *
 * 1. AuthenticationError
 * 2. UnexpectedError
 * 3. UnknownError
 */
sealed class RecipeErrorCode : ErrorCode {

    data object AuthenticationError: RecipeErrorCode()

    data object UnexpectedError: RecipeErrorCode()

    data object UnknownError: RecipeErrorCode()

}