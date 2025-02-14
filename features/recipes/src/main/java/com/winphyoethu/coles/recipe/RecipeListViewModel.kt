package com.winphyoethu.coles.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.features.recipe.R
import com.winphyoethu.coles.model.recipe.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(val recipeRepository: RecipeRepository) :
    ViewModel() {

    private val _errorState = MutableSharedFlow<Int>()
    val errorState = _errorState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L)
    )

    private val _recipeListUiState = MutableStateFlow<RecipeListUiState>(RecipeListUiState.Loading)
    val recipeListUiState = _recipeListUiState.onStart {
        getRecipe()
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = RecipeListUiState.Loading
        )

    fun getRecipe() {
        viewModelScope.launch {
            _recipeListUiState.emit(RecipeListUiState.Loading)

            when (val result = recipeRepository.getRecipeList()) {
                is ColesResult.Success -> {
                    _recipeListUiState.emit(RecipeListUiState.ShowRecipeList(result.data))
                }

                is ColesResult.Error -> {
                    val stringResId = when (result.e as RecipeErrorCode) {
                        RecipeErrorCode.AuthenticationError -> R.string.authentication_error
                        RecipeErrorCode.UnexpectedError -> R.string.unexpected_error
                        RecipeErrorCode.UnknownError -> R.string.unknown_error
                    }

                    _recipeListUiState.emit(RecipeListUiState.RecipeListError(stringResId))
                    _errorState.emit(stringResId)
                }
            }
        }
    }

    fun retry() {
        getRecipe()
    }

}

/**
 * Ui State for Recipe
 */
sealed class RecipeListUiState {

    data object Loading : RecipeListUiState()

    data class ShowRecipeList(val recipeList: PersistentList<Recipe>) : RecipeListUiState()

    data class RecipeListError(val stringResId: Int) : RecipeListUiState()

}