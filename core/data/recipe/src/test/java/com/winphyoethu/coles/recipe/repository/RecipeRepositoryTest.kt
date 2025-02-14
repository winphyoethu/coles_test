package com.winphyoethu.coles.recipe.repository

import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.model.recipe.mockRecipe
import com.winphyoethu.coles.network.BuildConfig
import com.winphyoethu.coles.recipe.RecipeRepositoryImpl
import com.winphyoethu.coles.recipe.remote.datasource.RecipeRemoteDatasource
import com.winphyoethu.coles.recipe.remote.model.RemoteRecipeList
import com.winphyoethu.coles.recipe.remote.model.mockRemoteRecipe
import com.winphyoethu.coles.recipe.remote.model.mockRemoteRecipeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.exceptions.base.MockitoException
import retrofit2.Response
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class RecipeRepositoryTest {

    private val remoteDatasource = mock<RecipeRemoteDatasource>()
    private lateinit var recipeRepository: RecipeRepository

    @Before
    fun setUp() {
        recipeRepository = RecipeRepositoryImpl(remoteDatasource, Dispatchers.Unconfined)
    }

    @Test
    fun `Get Recipe List return Success`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.success(mockRemoteRecipeList)
            )

            val result = recipeRepository.getRecipeList()

            assertEquals(ColesResult.Success::class.simpleName, result::class.simpleName)
        }
    }

    @Test
    fun `Check if thumbnail is attached base url in Get Recipe List`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.success(mockRemoteRecipeList)
            )

            val result = recipeRepository.getRecipeList()

            assertEquals(ColesResult.Success::class.simpleName, result::class.simpleName)

            val recipeList = result as ColesResult.Success
            assertContains(recipeList.data[0].dynamicThumbnail, BuildConfig.BASE_URL)
        }
    }

    @Test
    fun `Get Recipe List return Authentication Error`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.error(401, "Authentication Error".toResponseBody())
            )

            val result = recipeRepository.getRecipeList()

            assertEquals(ColesResult.Error(RecipeErrorCode.AuthenticationError), result)
        }
    }

    @Test
    fun `Get Recipe List return Unknown Error`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.error(500, "Unknown Error".toResponseBody())
            )

            val result = recipeRepository.getRecipeList()

            assertEquals(ColesResult.Error(RecipeErrorCode.UnknownError), result)
        }
    }

    @Test
    fun `Get Recipe List return Unexpected Error`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).doThrow(MockitoException("Unexpected Error"))

            val result = recipeRepository.getRecipeList()

            assertEquals(ColesResult.Error(RecipeErrorCode.UnexpectedError), result)
        }
    }

    @Test
    fun `Get Recipe By Id return Success`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.success(mockRemoteRecipeList)
            )

            val result = recipeRepository.getRecipeById(mockRemoteRecipe.dynamicTitle)

            assertEquals(ColesResult.Success::class.simpleName, result::class.simpleName)
        }
    }

    @Test
    fun `Check if thumbnail is attached base url in Get Recipe By Id`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.success(mockRemoteRecipeList)
            )

            val result = recipeRepository.getRecipeById(mockRemoteRecipe.dynamicTitle)

            assertEquals(ColesResult.Success::class.simpleName, result::class.simpleName)

            val recipe = result as ColesResult.Success
            assertContains(recipe.data.dynamicThumbnail, BuildConfig.BASE_URL)
        }
    }

    @Test
    fun `Check if Get Recipe by Id return correct value`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.success(
                    RemoteRecipeList(
                        listOf(
                            mockRemoteRecipe,
                            mockRemoteRecipe.copy(dynamicTitle = "Simple Curry")
                        )
                    )
                )
            )

            val result = recipeRepository.getRecipeById(mockRemoteRecipe.dynamicTitle)

            assertEquals(ColesResult.Success::class.simpleName, result::class.simpleName)

            val recipe = result as ColesResult.Success
            assertContains(recipe.data.dynamicTitle, mockRemoteRecipe.dynamicTitle)
        }
    }

    @Test
    fun `Get Recipe By Id return Authentication Error`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.error(401, "Authentication Error".toResponseBody())
            )

            val result = recipeRepository.getRecipeById(mockRecipe.dynamicTitle)

            assertEquals(ColesResult.Error(RecipeErrorCode.AuthenticationError), result)
        }
    }

    @Test
    fun `Get Recipe By Id return Unknown Error`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).thenReturn(
                Response.error(500, "Unknown Error".toResponseBody())
            )

            val result = recipeRepository.getRecipeById(mockRecipe.dynamicTitle)

            assertEquals(ColesResult.Error(RecipeErrorCode.UnknownError), result)
        }
    }

    @Test
    fun `Get Recipe By Id return Unexpected Error`() {
        runTest {
            whenever(remoteDatasource.getRecipe()).doThrow(MockitoException("Unexpected Error"))

            val result = recipeRepository.getRecipeById(mockRecipe.dynamicTitle)

            assertEquals(ColesResult.Error(RecipeErrorCode.UnexpectedError), result)
        }
    }

}