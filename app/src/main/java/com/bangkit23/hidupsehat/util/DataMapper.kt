package com.bangkit23.hidupsehat.util

import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.response.FeedResponse
import com.bangkit23.hidupsehat.data.source.remote.response.FeedResult
import com.bangkit23.hidupsehat.data.source.remote.response.UserDetailResult
import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.model.user.UserDetail
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.presentation.model.User
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData

fun UserData.toUser(): User {
    return User(
        userId = userId,
        name = username,
        photoUrl = profilePictureUrl,
    )
}

fun FoodEntity.toDomain(): Food = Food(
    id, name, portionSize, energyKj, energyKKal, sugar,
    potassium, carbohydrate, cholesterol, fat, saturatedFat,
    transFat, polyunsaturatedFat, monounsaturatedFat,
    protein, fiber, sodium
)

fun List<FoodEntity>.toDomain(): List<Food> = map(FoodEntity::toDomain)

fun Food.toEntity(): FoodEntity = FoodEntity(
    id, name, portionSize, energyKj, energyKKal, sugar,
    potassium, carbohydrate, cholesterol, fat, saturatedFat,
    transFat, polyunsaturatedFat, monounsaturatedFat,
    protein, fiber, sodium
)

fun UserDetailRequestDto.toRemote() = UserDetailRequest(
    username = username,
    name = name,
    contactNumber = contactNumber,
    dateOfBirth = dateOfBirth,
    age = age,
    gender = gender,
    height = height,
    weight = weight,
    target = target,
    weightTarget = weightTarget,
)

fun UserDetailResult.toDomain() = UserDetail(
    photoUrl = photoUrl,
    currentWeight = currentWeight,
    gender = gender,
    name = name,
    targetWeight = targetWeight,
    userId = userId,
    targetUser = targetUser,
    age = age,
    height = height
)

fun List<FeedResult>.toDomainn(): List<Feed> {
    return map { feedResult ->
        Feed(
            summary = feedResult.summary,
            imgUrl = feedResult.imgUrl,
            createdAt = feedResult.createdAt,
            author = feedResult.author,
            link = feedResult.link,
            title = feedResult.title,
            key = feedResult.key
        )
    }
}