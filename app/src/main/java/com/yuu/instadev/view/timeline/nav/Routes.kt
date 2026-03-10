package com.yuu.instadev.view.timeline.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class TimelineRoutes: NavKey {
    @Serializable
    data object Home: TimelineRoutes()
    @Serializable
    data object Search: TimelineRoutes()
    @Serializable
    data object Profile: TimelineRoutes()
}