package com.winphyoethu.coles.recipe

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winphyoethu.coles.designsystem.basiccomponent.ColesButton
import com.winphyoethu.coles.designsystem.basiccomponent.ColesSubTitle
import com.winphyoethu.coles.designsystem.basiccomponent.ColesTitle
import com.winphyoethu.coles.designsystem.colescomponent.RecipeCard
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.features.recipe.R
import com.winphyoethu.coles.model.recipe.Recipe
import com.winphyoethu.coles.model.recipe.mockRecipe
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.collectLatest
import com.winphyoethu.coles.designsystem.R as designSystemR

@Composable
internal fun RecipeListRoute(
    onRecipeClick: (recipe: Recipe) -> Unit,
    onShowSnackbar: suspend (String?) -> Unit,
    viewmodel: RecipeListViewModel = hiltViewModel()
) {
    val recipeListUiState by viewmodel.recipeListUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.errorState.collectLatest {
            onShowSnackbar(context.getString(it))
        }
    }

    RecipeListScreen(recipeListUiState, onRecipeClick, viewmodel::retry)
}

@Composable
internal fun RecipeListScreen(
    recipeListUiState: RecipeListUiState,
    onRecipeClick: (recipe: Recipe) -> Unit,
    retryClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (recipeListUiState) {
            RecipeListUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is RecipeListUiState.RecipeListError -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ColesSubTitle(
                        subtitle = stringResource(recipeListUiState.stringResId)
                    )
                    ColesButton(text = stringResource(R.string.retry)) {
                        retryClick()
                    }
                }
            }

            is RecipeListUiState.ShowRecipeList -> {
                val configuration = LocalConfiguration.current
                val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

                Column(
                    verticalArrangement = Arrangement.spacedBy(mediumDp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(designSystemR.drawable.coleslogo),
                        contentDescription = stringResource(R.string.coles_logo),
                        modifier = Modifier.size(100.dp, 50.dp).padding(top = mediumDp)
                    )
                    ColesTitle(title = stringResource(R.string.discover_recipe_title))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(if (isLandscape) 2 else 1), // 2 columns in landscape, 1 in portrait
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(mediumDp)
                    ) {
                        items(items = recipeListUiState.recipeList, key = { it.dynamicThumbnail }) {
                            RecipeCard(recipe = it) { recipe ->
                                onRecipeClick(recipe)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
private fun RecipeListScreenPreview() {
    ColesTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RecipeListScreen(
                recipeListUiState = RecipeListUiState.ShowRecipeList(
                    persistentListOf(mockRecipe, mockRecipe.copy(dynamicThumbnail = "gg"))
                ), retryClick = {

                }, onRecipeClick = { _ ->

                }
            )
        }
    }
}