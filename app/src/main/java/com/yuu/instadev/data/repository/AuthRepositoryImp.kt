package com.yuu.instadev.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.yuu.instadev.data.datasource.api.ApiService
import com.yuu.instadev.data.response.UserResponse
import com.yuu.instadev.data.response.toDomain
import com.yuu.instadev.domain.entity.UserEntity
import com.yuu.instadev.domain.entity.UserFirebaseEntity
import com.yuu.instadev.domain.repository.AuthRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(val api: ApiService, val firebaseAuth: FirebaseAuth): AuthRepository {
    override suspend fun doLogin(user: String, password: String): List<UserEntity> {
        val res:List<UserResponse> = try {
            api.login()
        }catch (e: Exception){
            Log.e("Yuu", "error on doLogin \n$e")
            listOf()
        }
        return res.map { it.toDomain() }
    }

    override suspend fun doLoginFirebase(
        email: String,
        password: String
    ): UserFirebaseEntity {
        val res = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()

        val user = res.user ?: throw IllegalStateException("Incorrect credentials")

        return UserFirebaseEntity(
            userID = user.uid,
            email = user.email
        )
    }

    override suspend fun doSignUpFirebase(email: String, password: String): UserFirebaseEntity {
        val res  = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()

        val user = res.user ?: throw IllegalStateException("Email already in use")

        return UserFirebaseEntity(
            userID = user.uid,
            email = user.email
        )
    }

}