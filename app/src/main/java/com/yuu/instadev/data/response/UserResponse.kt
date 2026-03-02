package com.yuu.instadev.data.response

import com.yuu.instadev.domain.entity.UserEntity
import com.yuu.instadev.domain.entity.UserType.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val userID: String,
    @SerialName("na-me") val name: String,
    val nickname: String,
    val followers: Int,
    val following: List<String>,
    val userType: Int
)

fun UserResponse.toDomain(): UserEntity{
    val userType = when(userType){
        REGULAR_USER.userType -> REGULAR_USER
        CONTENT_CREATOR_USER.userType -> CONTENT_CREATOR_USER
        CONTENT_CREATOR_USER.userType -> COMPANY_USER
        else -> REGULAR_USER
    }

    return UserEntity(
        userID = userID,
        name = name,
        nickname = nickname,
        followers = followers,
        following = following,
        userType = userType
    )
}