package com.yuu.instadev.view.core.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey{
    @Serializable
    data object Login: Routes()
    @Serializable
    data object SignUp: Routes()
    @Serializable
    data object Error: Routes()
}