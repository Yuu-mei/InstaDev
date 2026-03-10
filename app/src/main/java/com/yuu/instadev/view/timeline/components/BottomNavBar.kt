package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaText
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Home
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Profile
import com.yuu.instadev.view.timeline.nav.TimelineRoutes.Search

@Composable
fun BottomNavBar(
    currentScreen: NavKey?,
    onNavigate: (NavKey) -> Unit
){
    InstaNavigationBar() {
        //Home
        InstaNavigationItem(
            selected = currentScreen is Home,
            onClick = { onNavigate(Home) },
            icon = {
                Icon(
                    modifier = Modifier.width(30.dp),
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = "Home Button"
                )
            },
            label = {
                InstaText(
                    text = stringResource(R.string.nav_bottom_bar_home_text),
                    color = LocalContentColor.current
                )
            },
        )
        //Reels
        InstaNavigationItem(
            selected = false,
            enabled = false,
            onClick = {},
            icon = {
                Icon(
                    modifier = Modifier.width(30.dp),
                    painter = painterResource(R.drawable.ic_reels),
                    contentDescription = "Reels Button"
                )
            },
            label = {
                InstaText(
                    text = stringResource(R.string.nav_bottom_bar_reels_text),
                    color = LocalContentColor.current
                )
            }
        )
        //Messages
        InstaNavigationItem(
            selected = false,
            enabled = false,
            onClick = {},
            icon = {
                Icon(
                    modifier = Modifier.width(30.dp),
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = "Messages Button"
                )
            },
            label = {
                InstaText(
                    text = stringResource(R.string.nav_bottom_bar_messages_text),
                    color = LocalContentColor.current
                )
            }
        )
        //Search
        InstaNavigationItem(
            selected = currentScreen is Search,
            enabled = true,
            onClick = { onNavigate(Search) },
            icon = {
                Icon(
                    modifier = Modifier.width(30.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search Button"
                )
            },
            label = {
                InstaText(
                    text = stringResource(R.string.nav_bottom_bar_search_text),
                    color = LocalContentColor.current
                )
            }
        )
        //Profile
        InstaNavigationItem(
            selected = currentScreen is Profile,
            onClick = { onNavigate(Profile) },
            icon = {
                Icon(
                    modifier = Modifier.width(30.dp),
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "Profile Button"
                )
            },
            label = {
                InstaText(
                    text = stringResource(R.string.nav_bottom_bar_profile_text),
                    color = LocalContentColor.current
                )
            }
        )
    }
}