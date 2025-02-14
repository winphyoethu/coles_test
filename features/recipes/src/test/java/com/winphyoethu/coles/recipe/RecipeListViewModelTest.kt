package com.winphyoethu.coles.recipe

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.model.recipe.mockRecipe
import kotlinx.collections.immutable.persistentListOf
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
class RecipeListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val recipeRepository: RecipeRepository = mock()
    private lateinit var recipeListViewModel: RecipeListViewModel

    @Before
    fun setUp() {
        recipeListViewModel = RecipeListViewModel(recipeRepository)
    }

    @Test
    fun `Get Recipe Emit ShowRecipeList`() {
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.recipeListUiState.collect() }
            whenever(recipeRepository.getRecipeList()).thenReturn(
                ColesResult.Success(persistentListOf(mockRecipe))
            )

            recipeListViewModel.getRecipe()

            assertEquals(
                RecipeListUiState.ShowRecipeList(persistentListOf(mockRecipe)),
                recipeListViewModel.recipeListUiState.value
            )
        }
    }

    @Test
    fun `Get Recipe Emit Authentication Error`() {
        runTest {
            var resId = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.recipeListUiState.collect() }
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.errorState.collectLatest {
                resId = it
            } }

            whenever(recipeRepository.getRecipeList()).thenReturn(
                ColesResult.Error(RecipeErrorCode.AuthenticationError)
            )

            recipeListViewModel.getRecipe()

            assertEquals(
                RecipeListUiState.RecipeListError(R.string.authentication_error),
                recipeListViewModel.recipeListUiState.value
            )

            assertEquals(R.string.authentication_error, resId)
        }
    }

    @Test
    fun `Get Recipe Emit Unknown Error`() {
        runTest {
            var resId = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.recipeListUiState.collect() }
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.errorState.collectLatest {
                resId = it
            } }

            whenever(recipeRepository.getRecipeList()).thenReturn(
                ColesResult.Error(RecipeErrorCode.UnknownError)
            )

            recipeListViewModel.getRecipe()

            assertEquals(
                RecipeListUiState.RecipeListError(R.string.unknown_error),
                recipeListViewModel.recipeListUiState.value
            )

            assertEquals(R.string.unknown_error, resId)
        }
    }

    @Test
    fun `Get Recipe Emit Unexpected Error`() {
        runTest {
            var resId = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.recipeListUiState.collect() }
            backgroundScope.launch(UnconfinedTestDispatcher()) { recipeListViewModel.errorState.collectLatest {
                resId = it
            } }

            whenever(recipeRepository.getRecipeList()).thenReturn(
                ColesResult.Error(RecipeErrorCode.UnexpectedError)
            )

            recipeListViewModel.getRecipe()
            assertEquals(
                RecipeListUiState.RecipeListError(R.string.unexpected_error),
                recipeListViewModel.recipeListUiState.value
            )

            assertEquals(R.string.unexpected_error, resId)
        }
    }

}