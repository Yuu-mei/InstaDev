package com.yuu.instadev.data.repository

import android.util.Log
import com.yuu.instadev.data.datasource.api.ApiService
import com.yuu.instadev.data.response.UserResponse
import com.yuu.instadev.data.response.toDomain
import com.yuu.instadev.domain.entity.UserEntity
import com.yuu.instadev.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(val api: ApiService): AuthRepository {
    override suspend fun doLogin(user: String, password: String): List<UserEntity> {
        val res:List<UserResponse> = try {
            api.login()
        }catch (e: Exception){
            Log.e("Yuu", "error on doLogin \n$e")
            listOf()
        }
        return res.map { it.toDomain() }
    }

}