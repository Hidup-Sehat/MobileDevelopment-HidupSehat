package com.bangkit23.hidupsehat.util

import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import com.bangkit23.hidupsehat.domain.model.food.Food
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