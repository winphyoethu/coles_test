package com.winphyoethu.coles.network.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.winphyoethu.coles.network.BuildConfig
import com.winphyoethu.coles.network.interceptor.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Named("MockInterceptor")
    @Provides
    fun providesMockInterceptor(@ApplicationContext context: Context): Interceptor {
        return MockInterceptor(context)
    }

    @Provides
    @Singleton
    fun providesOkHttp(@Named("MockInterceptor") interceptor: Interceptor): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        val adapterFactory = KotlinJsonAdapterFactory()
        return Moshi.Builder().add(adapterFactory).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okhttpFactory: Call.Factory, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .callFactory(okhttpFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

}