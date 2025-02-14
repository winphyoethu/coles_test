package com.winphyoethu.coles.recipe

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.model.recipe.mockRecipe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import com.winphyoethu.coles.features.recipe.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class RecipeDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val recipeRepository: RecipeRepository = mock()
    private lateinit var recipeDetailViewModel: RecipeDetailViewModel

    @Before
    fun setUp() {
        recipeDetailViewModel = RecipeDetailViewModel(recipeRepository)
    }

    @Test
    fun `Get Recipe by Id Emit ShowRecipe`() {
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.recipeDetailUiState.collect() }
            whenever(recipeRepository.getRecipeById(any())).thenReturn(
                ColesResult.Success(mockRecipe)
            )

            recipeDetailViewModel.getRecipeById("id")

            assertEquals(
                RecipeDetailUiState.ShowRecipe(mockRecipe),
                recipeDetailViewModel.recipeDetailUiState.value
            )
        }
    }

    @Test
    fun `Get Recipe by Id Emit Authentication Error`() {
        runTest {
            var resId = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.recipeDetailUiState.collect() }
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.errorState.collectLatest {
                resId = it
            } }

            whenever(recipeRepository.getRecipeById(any())).thenReturn(
                ColesResult.Error(RecipeErrorCode.AuthenticationError)
            )

            recipeDetailViewModel.getRecipeById("id")

            assertEquals(
                RecipeDetailUiState.RecipeError(R.string.authentication_error),
                recipeDetailViewModel.recipeDetailUiState.value
            )

            assertEquals(R.string.authentication_error, resId)
        }
    }

    @Test
    fun `Get Recipe by Id Emit Unknown Error`() {
        runTest {
            var resId = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.recipeDetailUiState.collect() }
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.errorState.collectLatest {
                resId = it
            } }

            whenever(recipeRepository.getRecipeById(any())).thenReturn(
                ColesResult.Error(RecipeErrorCode.UnknownError)
            )

            recipeDetailViewModel.getRecipeById("id")

            assertEquals(
                RecipeDetailUiState.RecipeError(R.string.unknown_error),
                recipeDetailViewModel.recipeDetailUiState.value
            )

            assertEquals(R.string.unknown_error, resId)
        }
    }

    @Test
    fun `Get Recipe by Id Emit Unexpected Error`() {
        runTest {
            var resId = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.recipeDetailUiState.collect() }
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeDetailViewModel.errorState.collectLatest {
                resId = it
            } }

            whenever(recipeRepository.getRecipeById(any())).thenReturn(
                ColesResult.Error(RecipeErrorCode.UnexpectedError)
            )

            recipeDetailViewModel.getRecipeById("id")

            assertEquals(
                RecipeDetailUiState.RecipeError(R.string.unexpected_error),
                recipeDetailViewModel.recipeDetailUiState.value
            )

            assertEquals(R.string.unexpected_error, resId)
        }
    }

}