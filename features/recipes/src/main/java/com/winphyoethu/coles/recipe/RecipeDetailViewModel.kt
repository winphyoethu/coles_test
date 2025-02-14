package com.winphyoethu.coles.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.coles.common.result.ColesResult
import com.winphyoethu.coles.domain.recipe.errorcode.RecipeErrorCode
import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.features.recipe.R
import com.winphyoethu.coles.model.recipe.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(val recipeRepository: RecipeRepository) : ViewModel() {

    private val _errorState = MutableSharedFlow<Int>()
    val errorState = _errorState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L)
    )

    private val _recipeDetailUiState = MutableStateFlow<RecipeDetailUiState>(RecipeDetailUiState.Loading)
    val recipeDetailUiState = _recipeDetailUiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = RecipeDetailUiState.Loading
        )

    fun getRecipeById(id: String) {
        viewModelScope.launch {
            _recipeDetailUiState.emit(RecipeDetailUiState.Loading)

            when (val result = recipeRepository.getRecipeById(id)) {
                is ColesResult.Success -> {
                    _recipeDetailUiState.emit(RecipeDetailUiState.ShowRecipe(result.data))
                }

                is ColesResult.Error -> {
                    val stringResId = when (result.e as RecipeErrorCode) {
                        RecipeErrorCode.AuthenticationError -> R.string.authentication_error
                        RecipeErrorCode.UnexpectedError -> R.string.unexpected_error
                        RecipeErrorCode.UnknownError -> R.string.unknown_error
                    }

                    _recipeDetailUiState.emit(RecipeDetailUiState.RecipeError(stringResId))
                    _errorState.emit(stringResId)
                }
            }
        }
    }

}

/**
 * Ui State for Recipe Detail
 */
sealed class RecipeDetailUiState {

    data object Loading : RecipeDetailUiState()

    data class ShowRecipe(val recipe: Recipe) : RecipeDetailUiState()

    data class RecipeError(val stringResId: Int) : RecipeDetailUiState()

}