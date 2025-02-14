package com.winphyoethu.coles.recipe

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.winphyoethu.coles.designsystem.basiccomponent.ColesAppbar
import com.winphyoethu.coles.designsystem.basiccomponent.ColesButton
import com.winphyoethu.coles.designsystem.basiccomponent.ColesSubTitle
import com.winphyoethu.coles.designsystem.basiccomponent.ColesTitle
import com.winphyoethu.coles.designsystem.colescomponent.RecipeDetailCard
import com.winphyoethu.coles.designsystem.colescomponent.RecipeIngredient
import com.winphyoethu.coles.designsystem.ui.theme.ColesIcons
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.largeDp
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.designsystem.ui.theme.recipeImageSize
import com.winphyoethu.coles.features.recipe.R
import com.winphyoethu.coles.model.recipe.mockRecipe
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun RecipeDetailRoute(
    recipeId: String,
    backClick: () -> Unit,
    onShowSnackbar: suspend (String?) -> Unit,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val id = rememberSaveable { recipeId }
    val recipeDetailUiState by viewModel.recipeDetailUiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.errorState.collectLatest {
            onShowSnackbar(context.getString(it))
        }
    }

    LaunchedEffect(id) {
        viewModel.getRecipeById(id)
    }

    RecipeDetailScreen(
        recipeDetailUiState,
        retryClick = { viewModel.getRecipeById(id) },
        backClick = backClick
    )
}

@Composable
internal fun RecipeDetailScreen(
    recipeDetailUiState: RecipeDetailUiState,
    retryClick: () -> Unit,
    backClick: () -> Unit
) {
    Column {
        ColesAppbar(
            icon = ColesIcons.Back,
            iconDescription = stringResource(R.string.back_button),
            title = stringResource(R.string.recipe_detail),
            iconAction = { backClick() },
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (recipeDetailUiState) {
                RecipeDetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is RecipeDetailUiState.RecipeError -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ColesSubTitle(
                            subtitle = stringResource(recipeDetailUiState.stringResId)
                        )
                        ColesButton(text = stringResource(R.string.retry)) {
                            retryClick()
                        }
                    }
                }

                is RecipeDetailUiState.ShowRecipe -> {
                    val configuration = LocalConfiguration.current
                    val isLandscape =
                        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

                    if (isLandscape) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(mediumDp),
                            modifier = Modifier.padding(start = mediumDp, end = mediumDp)
                        ) {
                            item {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                    ) {
                                        AsyncImage(
                                            model = recipeDetailUiState.recipe.dynamicThumbnail,
                                            contentDescription = recipeDetailUiState.recipe.dynamicThumbnailAlt,
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .height(recipeImageSize)
                                                .clip(RoundedCornerShape(largeDp))
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                            .padding(start = largeDp),
                                        verticalArrangement = Arrangement.spacedBy(mediumDp)
                                    ) {
                                        ColesTitle(
                                            title = recipeDetailUiState.recipe.dynamicTitle,
                                        )
                                        RecipeDetailCard(
                                            recipeDetailUiState.recipe.recipeDetail,
                                            arrangement = Arrangement.Start
                                        )
                                    }
                                }
                            }
                            item { ColesTitle(title = stringResource(R.string.recipe_detail_ingredients)) }
                            items(items = recipeDetailUiState.recipe.ingredientList, key = { it }) {
                                RecipeIngredient(ingredient = it)
                            }
                        }
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(mediumDp),
                            modifier = Modifier.padding(start = mediumDp, end = mediumDp)
                        ) {
                            item {
                                AsyncImage(
                                    model = recipeDetailUiState.recipe.dynamicThumbnail,
                                    contentDescription = recipeDetailUiState.recipe.dynamicThumbnailAlt,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(recipeImageSize)
                                        .clip(RoundedCornerShape(largeDp))
                                )
                            }
                            item { ColesTitle(title = recipeDetailUiState.recipe.dynamicTitle) }
                            item { RecipeDetailCard(recipeDetailUiState.recipe.recipeDetail) }
                            item { ColesTitle(title = "Ingredients") }
                            items(items = recipeDetailUiState.recipe.ingredientList, key = { it }) {
                                RecipeIngredient(ingredient = it)
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
private fun RecipeDetailScreenPreview() {
    ColesTheme(dynamicColor = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            RecipeDetailScreen(
                RecipeDetailUiState.ShowRecipe(mockRecipe),
                retryClick = {},
                backClick = {})
        }
    }
}