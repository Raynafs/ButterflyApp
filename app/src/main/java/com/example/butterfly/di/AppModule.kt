package com.example.butterfly.di

import com.example.butterfly.data.ButterflyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn (SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideButterflyApi(): ButterflyApi{
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ButterflyApi.BASE_URL)
            .client(httpClient)
            .build()
            .create(ButterflyApi::class.java)
    }

}