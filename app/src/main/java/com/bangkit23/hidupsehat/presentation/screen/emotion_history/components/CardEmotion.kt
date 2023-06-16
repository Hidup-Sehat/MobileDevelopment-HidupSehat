package com.bangkit23.hidupsehat.presentation.screen.emotion_history.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.food_information_detail.component.DetailItem

@Composable
fun CardEmotion(
    modifier: Modifier = Modifier,
    positive : String,
    negative : String,
    source : String,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (cv1, cv2, cv3, cv4) = createRefs()
        DetailItem(
            modifier = Modifier
                .constrainAs(cv1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(cv2.start, 10.dp)
                    width = Dimension.fillToConstraints
                },
            title = "Statistik Hari",
            data = "-",
            color = colorResource(id = R.color.green_tile)
        )

        DetailItem(
            modifier = Modifier.constrainAs(cv2) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(cv1.end)
                width = Dimension.fillToConstraints
            },
            title = "Emosi Positif",
            data = positive,
            color = colorResource(id = R.color.blue_tile)
        )
        DetailItem(
            modifier = Modifier.constrainAs(cv3) {
                top.linkTo(cv1.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(cv4.start, 10.dp)
                width = Dimension.fillToConstraints
            },
            title = "Asal Emosi",
            data = source,
            color = colorResource(id = R.color.yellow_tile)
        )
        DetailItem(
            modifier = Modifier.constrainAs(cv4) {
                top.linkTo(cv2.bottom, 10.dp)
                start.linkTo(cv3.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            title = "Emosi Negatif", data = negative, color = colorResource(id = R.color.red_tile)
        )
    }
}