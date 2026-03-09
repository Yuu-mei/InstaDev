package com.yuu.instadev.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.yuu.instadev.data.datasource.api.ApiService
import com.yuu.instadev.data.response.UserResponse
import com.yuu.instadev.data.response.toDomain
import com.yuu.instadev.domain.entity.UserEntity
import com.yuu.instadev.domain.entity.UserFirebaseEntity
import com.yuu.instadev.domain.repository.AuthRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AuthRepositoryImp @Inject constructor(val api: ApiService, val firebaseAuth: FirebaseAuth, val fireStore: FirebaseFirestore): AuthRepository {
    override suspend fun doLogin(user: String, password: String): List<UserEntity> {
        val res:List<UserResponse> = try {
            api.login()
        }catch (e: Exception){
            Log.e("Yuu", "error on doLogin \n$e")
            listOf()
        }
        return res.map { it.toDomain() }
    }

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun doLoginFirebase(
        email: String,
        password: String
    ): UserFirebaseEntity {
        val res = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()

        val user = res.user ?: throw IllegalStateException("Incorrect credentials")
        if(!user.isEmailVerified){
            throw Exception("Please verify your email")
        }else {
            //Create the user collection for the user in case they are a new user
            val uid = user.uid
            val userDoc = fireStore.collection("users").document(uid)
            val snapshot = userDoc.get().await()

            if(snapshot.exists()) return UserFirebaseEntity(userID = user.uid, email = user.email)
            val data = mapOf(
                "userID" to uid,
                "email" to user.email,
                "username" to "Test_User_${Uuid.random()}",
                "bio" to "",
                "profilePic" to "https://randomuser.me/portraits/men/${Random.nextInt(50)}.jpg",
                "createdAt" to FieldValue.serverTimestamp()
            )

            userDoc.set(data).await()

            return UserFirebaseEntity(
                userID = user.uid,
                email = user.email
            )
        }
    }

    override suspend fun doSignUpFirebase(email: String, password: String): UserFirebaseEntity {
        val res  = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()

        val user = res.user ?: throw IllegalStateException("Email already in use")
        user.sendEmailVerification()

        return UserFirebaseEntity(
            userID = user.uid,
            email = user.email
        )
    }

}