package com.yuu.instadev.domain.entity

data class UserEntity(
    val userID: String,
    val name: String,
    val nickname: String,
    val followers: Int,
    val following: List<String>,
    val userType: UserType
)

sealed class UserType(val userType: Int){
    data object REGULAR_USER: UserType(0)
    data object CONTENT_CREATOR_USER: UserType(1)
    data object COMPANY_USER: UserType(2)
}