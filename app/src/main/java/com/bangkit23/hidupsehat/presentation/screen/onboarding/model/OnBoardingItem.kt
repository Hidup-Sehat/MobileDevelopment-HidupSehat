package com.bangkit23.hidupsehat.presentation.screen.onboarding.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.bangkit23.hidupsehat.R

data class OnBoardingItem(
    val title: String,
    val description: AnnotatedString,
    val image: Int
)

object OnBoardingData {
    val onBoardingItems = listOf(
        OnBoardingItem(
            title = "Kenali Gizi Makananmu\ndengan Praktis",
            description = buildAnnotatedString {
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("Artificial Intelligence ")
                }
                append("membantumu \nuntuk menghitung nutrisi dan asupan \nmakananmu dengan ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("sekali foto!")
                }
            },
            image = R.drawable.on_boarding_page_1
        ),
        OnBoardingItem(
            title = "Berolahraga dan Meditasi\ndengan Tenang",
            description = buildAnnotatedString {
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("Artificial Intelligence ")
                }
                append("membantumu \nuntuk menciptakan aktivitas olahraga \ndan meditasi yang ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("berkualitas!")
                }
            },
            image = R.drawable.on_boarding_page_2
        ),
        OnBoardingItem(
            title = "Jaga Kesehatan Mentalmu\nagar Lebih Positif",
            description = buildAnnotatedString {
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("Artificial Intelligence ")
                }
                append("membantumu \nmemberikan rekomendasi artikel dan \nolahraga dengan ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("menceritakan harimu!")
                }
            },
            image = R.drawable.on_boarding_page_3
        )
    )
}
