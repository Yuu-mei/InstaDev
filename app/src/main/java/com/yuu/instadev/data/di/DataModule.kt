package com.yuu.instadev.data.di

import com.yuu.instadev.data.Config
import com.yuu.instadev.data.datasource.api.ApiService
import com.yuu.instadev.data.repository.AuthRepositoryImp
import com.yuu.instadev.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideAuthRepository(api: ApiService): AuthRepository{
        return AuthRepositoryImp(api)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(json: Json): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .build()
    }

    @Provides
    fun provideJson(): Json{
        return Json{
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}