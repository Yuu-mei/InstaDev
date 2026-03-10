package com.yuu.instadev.view.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun SearchScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        InstaText(text = "Search screen")
    }
}