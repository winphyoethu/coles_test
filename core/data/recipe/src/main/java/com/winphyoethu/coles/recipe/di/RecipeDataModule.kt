package com.winphyoethu.coles.recipe.di

import com.winphyoethu.coles.domain.recipe.repository.RecipeRepository
import com.winphyoethu.coles.recipe.RecipeRepositoryImpl
import com.winphyoethu.coles.recipe.remote.apiservice.RecipeService
import com.winphyoethu.coles.recipe.remote.datasource.RecipeRemoteDatasource
import com.winphyoethu.coles.recipe.remote.datasource.RecipeRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class RecipeApiModule {

    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

}

@InstallIn(SingletonComponent::class)
@Module
abstract class RecipeDataModule {

    @Binds
    internal abstract fun bindsRecipeRemoteDatasource(recipeRemoteDatasourceImpl: RecipeRemoteDatasourceImpl): RecipeRemoteDatasource

    @Binds
    internal abstract fun bindsRecipeRepository(recipeRepositoryImpl: RecipeRepositoryImpl): RecipeRepository

}