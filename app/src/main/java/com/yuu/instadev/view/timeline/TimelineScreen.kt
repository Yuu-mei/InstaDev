package com.yuu.instadev.view.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import com.yuu.instadev.view.timeline.components.BottomNavBar
import com.yuu.instadev.view.timeline.nav.TimelineNavigationWrapper
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Home

@Preview(
    showSystemUi = true
)
@Composable
fun TimelineScreen() {
    val tabBackStack = rememberNavBackStack(Home)

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentScreen = tabBackStack.lastOrNull(),
                onNavigate = { tab ->
                    tabBackStack.add(tab)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimelineNavigationWrapper(backStack = tabBackStack)
        }
    }
}