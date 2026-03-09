package com.yuu.instadev.view.core.nav

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.yuu.instadev.view.auth.login.LoginScreen
import com.yuu.instadev.view.auth.signup.SignUpScreen
import com.yuu.instadev.view.core.error.ErrorScreen
import com.yuu.instadev.view.core.nav.Routes.Login
import com.yuu.instadev.view.core.nav.Routes.SignUp
import com.yuu.instadev.view.core.nav.Routes.Error
import com.yuu.instadev.view.core.nav.Routes.Timeline
import com.yuu.instadev.view.timeline.TimelineScreen

@Composable
fun NavigationWrapper() {
    val backStack = rememberNavBackStack(Login)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen(
                    navigateToSignUp = { backStack.add(SignUp) },
                    navigateToTimeline = {
                        backStack.clear()
                        backStack.add(Timeline)
                    }
                )
            }
            entry<SignUp> {
                SignUpScreen() {
                    backStack.removeLastOrNull()
                }
            }
            entry<Error> {
                ErrorScreen() {
                    backStack.removeLastOrNull()
                }
            }
            entry<Timeline> {
                TimelineScreen()
            }
        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(1000)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(1000)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(1000)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(1000)
            )
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(1000)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(1000)
            )
        }
    )
}