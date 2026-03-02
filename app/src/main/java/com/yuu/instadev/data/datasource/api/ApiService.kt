package com.yuu.instadev.data.datasource.api

import com.yuu.instadev.data.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("login/.json")
    suspend fun login(): List<UserResponse>
}