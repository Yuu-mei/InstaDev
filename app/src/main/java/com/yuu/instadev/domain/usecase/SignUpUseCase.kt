package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.entity.UserFirebaseEntity
import com.yuu.instadev.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String): UserFirebaseEntity{
        return authRepository.doSignUpFirebase(email, password)
    }
}