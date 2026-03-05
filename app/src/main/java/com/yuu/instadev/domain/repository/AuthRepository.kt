package com.yuu.instadev.domain.repository

import com.yuu.instadev.domain.entity.UserEntity
import com.yuu.instadev.domain.entity.UserFirebaseEntity

interface AuthRepository {
    suspend fun doLogin(user: String, password: String): List<UserEntity>
    suspend fun doLoginFirebase(email: String, password: String): UserFirebaseEntity
    suspend fun doSignUpFirebase(email: String, password: String): UserFirebaseEntity
}