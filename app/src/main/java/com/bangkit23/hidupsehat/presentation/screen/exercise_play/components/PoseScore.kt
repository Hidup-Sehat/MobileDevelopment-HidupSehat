package com.bangkit23.hidupsehat.presentation.screen.exercise_play.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PoseScore(
    score: Double,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        ScoreProgressBar(
            percentage = score.toFloat(),
            strokeWidth = 24.dp,
            modifier = Modifier.align(Alignment.Center)
        )
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${(score * 100).toInt()}",
                style = MaterialTheme.typography.displayMedium,
                color = colorResource(R.color.white),
            )
            Spacer(Modifier.width(4.dp))
            Text(text = "%", color = colorResource(R.color.white))
        }
    }
}

@Composable
fun ScoreProgressBar(
    percentage: Float,
    strokeWidth: Dp,
    modifier: Modifier = Modifier,
    fillColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {
    Canvas(
        modifier = modifier
            .size(160.dp)
            .padding(8.dp)
    ) {
        drawArc(
            color = backgroundColor,
            140f,
            180f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = fillColor,
            140f,
            percentage * 180f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        val angleInDegrees = (percentage * 180.0) + 50.0
        val radius = (size.height / 2)
        val x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        val y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)

        drawCircle(
            color = Color.White,
            radius = 5f,
            center = Offset(x,  y)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PoseScorePreview() {
    HidupSehatTheme {
        PoseScore(
            score = 1.0,
        )
    }
}