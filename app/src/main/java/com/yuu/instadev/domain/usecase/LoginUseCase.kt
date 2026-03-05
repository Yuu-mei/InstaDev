package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.entity.UserFirebaseEntity
import com.yuu.instadev.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
//    suspend operator fun invoke(user: String, password: String): UserEntity?{
//        if(user.contains("@hotmail.com")) return null
//
//        val res = authRepository.doLogin(user, password);
//        return res.random()
//    }

    suspend operator fun invoke(email: String, password: String): UserFirebaseEntity{
        return authRepository.doLoginFirebase(email, password)
    }
}