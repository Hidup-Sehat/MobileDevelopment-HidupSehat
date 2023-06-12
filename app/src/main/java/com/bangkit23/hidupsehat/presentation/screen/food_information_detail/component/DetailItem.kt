package com.bangkit23.hidupsehat.presentation.screen.food_information_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bangkit23.hidupsehat.R


@Composable
fun CardFoodContent(
    modifier : Modifier = Modifier
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
            title = "Karbohidrat", data = "4,5g", color = colorResource(id = R.color.green_tile)
        )

        DetailItem(
            modifier = Modifier.constrainAs(cv2) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(cv1.end)
                width = Dimension.fillToConstraints
            },
            title = "Lemak", data = "4,5g", color = colorResource(id = R.color.blue_tile)
        )
        DetailItem(
            modifier = Modifier.constrainAs(cv3) {
                top.linkTo(cv1.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(cv4.start, 10.dp)
                width = Dimension.fillToConstraints
            },
            title = "Serat", data = "4,5g", color = colorResource(id = R.color.yellow_tile)
        )
        DetailItem(
            modifier = Modifier.constrainAs(cv4) {
                top.linkTo(cv2.bottom, 10.dp)
                start.linkTo(cv3.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            title = "Protein", data = "4,5g", color = colorResource(id = R.color.red_tile)
        )
    }
}

@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    title: String,
    data: String,
    color: Color
) {
    Box(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(19.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title, style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Light, color = Color.White
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold, color = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailItemPrev() {
//    DetailItem(title = "Karbohidrat", data = "44.0g", color = Color.Blue)
    CardFoodContent()
}