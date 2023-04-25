package com.example.butterfly.data

import retrofit2.http.GET

interface ButterflyApi {

    @GET("/butterfly")
    suspend fun getRandomButterfly(): Butterfly

    companion object{
        const val BASE_URL = "http://192.168.0.16:8080"
    }

}