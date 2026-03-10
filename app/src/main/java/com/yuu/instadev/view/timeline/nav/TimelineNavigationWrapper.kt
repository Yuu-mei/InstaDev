package com.yuu.instadev.view.timeline.nav

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.yuu.instadev.view.timeline.HomeScreen
import com.yuu.instadev.view.timeline.ProfileScreen
import com.yuu.instadev.view.timeline.SearchScreen
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Home
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Profile
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Search

@Composable
fun TimelineNavigationWrapper(backStack: NavBackStack<NavKey>) {
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Home> {
                HomeScreen()
            }
            entry<Search> {
                SearchScreen()
            }
            entry<Profile>{
                ProfileScreen()
            }
        }
    )
}