package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.entity.UserEntity
import com.yuu.instadev.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: String, password: String): UserEntity?{
        if(user.contains("@hotmail.com")) return null

        val res = authRepository.doLogin(user, password);
        return res.random()
    }
}