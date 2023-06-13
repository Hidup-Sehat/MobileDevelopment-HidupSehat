package com.bangkit23.hidupsehat.presentation.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.presentation.components.DotsIndicator
import com.bangkit23.hidupsehat.presentation.screen.onboarding.model.OnBoardingData
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    moveToLogin: () -> Unit,
) {
    val  pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (pager, buttonWithIndicator) = createRefs()
        HorizontalPager(
            pageCount = 3,
            state = pagerState,
            pageSpacing = 8.dp,
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttonWithIndicator.top)
                }
        ) { page ->
            OnBoardingPagerContent(
                imageResource = OnBoardingData.onBoardingItems[page].image,
                title = OnBoardingData.onBoardingItems[page].title,
                description = OnBoardingData.onBoardingItems[page].description
            )
        }
        ButtonSkipOrNext(
            onSkipClick = moveToLogin,
            onNextClick = {
                scope.launch {
                    if (pagerState.currentPage != 2) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        moveToLogin()
                    }
                }
            },
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .padding(24.dp)
                .constrainAs(buttonWithIndicator) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun OnBoardingPagerContent(
    imageResource: Int,
    title: String,
    description: AnnotatedString,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(Modifier.height(48.dp))
        AsyncImage(
            model = imageResource,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(24.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(96.dp))
    }
}

@Composable
fun ButtonSkipOrNext(
    totalDots: Int,
    selectedIndex: Int,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = onSkipClick
        ) {
            Text(
                text = "Lewati",
                color = MaterialTheme.colorScheme.outline
            )
        }
        DotsIndicator(
            totalDots = totalDots,
            selectedIndex = selectedIndex
        )
        TextButton(
            onClick = onNextClick
        ) {
            Text(
                text = "Lanjut",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3A)
@Composable
fun OnBoardingScreenPreview() {
    HidupSehatTheme {
        OnBoardingScreen(
            moveToLogin = {}
        )
    }
}