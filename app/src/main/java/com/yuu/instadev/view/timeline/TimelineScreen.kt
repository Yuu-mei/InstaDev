package com.yuu.instadev.view.timeline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import com.yuu.instadev.R
import com.yuu.instadev.view.timeline.components.BottomNavBar
import com.yuu.instadev.view.timeline.nav.TimelineNavigationWrapper
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Home

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true
)
@Composable
fun TimelineScreen() {
    val tabBackStack = rememberNavBackStack(Home)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.ic_instadev_company_logo),
                        contentDescription = "Instadev Company Logo"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = "Add new Reel",
                            tint = LocalContentColor.current
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
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