package com.yuu.instadev.domain.repository

import com.yuu.instadev.domain.entity.UserEntity

interface AuthRepository {
    suspend fun doLogin(user: String, password: String): List<UserEntity>
}