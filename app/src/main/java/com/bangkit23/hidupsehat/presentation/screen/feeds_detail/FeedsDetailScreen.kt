package com.bangkit23.hidupsehat.presentation.screen.feeds_detail

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsDetailScreen(
    id: String,
    onNavigateUp: () -> Unit,
    viewModel: FeedsDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(FeedDetailEvent.OnGetDetailFeed(id))
    }


    val state by viewModel.state.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateUp.invoke()
                        },
                        content = {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                        }
                    )
                }
            )
        },
        content = {
            FeedsDetailContent(
                modifier = Modifier.padding(it),
                linkUrl = state.link
            )
        }
    )
}

@Composable
fun WebViewLayout(linkUrl: String) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(linkUrl)
        }
    }, update = {
        it.loadUrl(linkUrl)
    })
}

@Composable
fun FeedsDetailContent(
    modifier: Modifier = Modifier,
    linkUrl: String
) {
    WebViewLayout(linkUrl)

}