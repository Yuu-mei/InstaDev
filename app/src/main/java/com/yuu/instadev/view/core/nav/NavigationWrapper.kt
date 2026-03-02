package com.yuu.instadev.view.core.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuu.instadev.view.auth.login.LoginScreen
import com.yuu.instadev.view.auth.signup.SignUpScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination= Login){
        composable<Login> {
            LoginScreen(){navController.navigate(SignUp)}
        }
        composable<SignUp> {
            SignUpScreen(){navController.popBackStack()}
        }
    }
}