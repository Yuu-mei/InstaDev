package com.yuu.instadev.view.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaNavigationBar
import com.yuu.instadev.view.core.components.InstaNavigationItem
import com.yuu.instadev.view.core.components.InstaText

@Preview(
    showSystemUi = true
)
@Composable
fun TimelineScreen() {

    Scaffold(
        bottomBar = {
            InstaNavigationBar() {
                //Home
                InstaNavigationItem(
                    selected = true,
                    onClick = {},
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
                    selected = false,
                    enabled = false,
                    onClick = {},
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
                    selected = false,
                    onClick = {},
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
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}