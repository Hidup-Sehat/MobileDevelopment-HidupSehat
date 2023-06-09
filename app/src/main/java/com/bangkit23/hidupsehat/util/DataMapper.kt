package com.bangkit23.hidupsehat.util

import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import com.bangkit23.hidupsehat.data.source.local.entity.ReminderEntity
import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.response.ActivityResponseItem
import com.bangkit23.hidupsehat.data.source.remote.response.AddEmotionDataResponse
import com.bangkit23.hidupsehat.data.source.remote.response.AddFoodsData
import com.bangkit23.hidupsehat.data.source.remote.response.AddFoodsItem
import com.bangkit23.hidupsehat.data.source.remote.response.AddPointsResponseData
import com.bangkit23.hidupsehat.data.source.remote.response.BodyAngleResponse
import com.bangkit23.hidupsehat.data.source.remote.response.DataDiary
import com.bangkit23.hidupsehat.data.source.remote.response.FeedDetailResponse
import com.bangkit23.hidupsehat.data.source.remote.response.FeedResult
import com.bangkit23.hidupsehat.data.source.remote.response.FoodHistoryDetailResponseItem
import com.bangkit23.hidupsehat.data.source.remote.response.FoodsHistoryResponseItem
import com.bangkit23.hidupsehat.data.source.remote.response.MovementResponseItem
import com.bangkit23.hidupsehat.data.source.remote.response.UserDetailResult
import com.bangkit23.hidupsehat.data.source.remote.response.UserNeedsResponse
import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.domain.model.activity.BodyAngle
import com.bangkit23.hidupsehat.domain.model.activity.MovementItem
import com.bangkit23.hidupsehat.domain.model.diary.Diary
import com.bangkit23.hidupsehat.domain.model.feed.DetailFeed
import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.domain.model.food.AddFoods
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.model.food.FoodHistoryDetailItem
import com.bangkit23.hidupsehat.domain.model.food.FoodItem
import com.bangkit23.hidupsehat.domain.model.food.FoodsHistoryItem
import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import com.bangkit23.hidupsehat.domain.model.user.AddPoints
import com.bangkit23.hidupsehat.domain.model.user.UserDetail
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.domain.model.user.UserNeeds
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

fun ReminderEntity.toDomain() = Reminder(
    id = id,
    title = title,
    time = time,
    type = type,
    isActive = isActive,
)

fun Reminder.toEntity() = ReminderEntity(
    id = id,
    title = title,
    time = time,
    type = type,
    isActive = isActive,
)

fun UserNeedsResponse.toDomain() = UserNeeds(
    gender = gender,
    burnedCalorie = burnedCalorie,
    sleepNeeds = sleepNeeds,
    registeredAt = registeredAt,
    weight = weight,
    dateOfBirth = dateOfBirth,
    target = target,
    weightTarget = weightTarget,
    actualSleep = actualSleep,
    imgUrl = imgUrl,
    actualWater = actualWater,
    waterNeeds = waterNeeds,
    name = name,
    contactNumber = contactNumber,
    calorieNeeds = calorieNeeds,
    id = id,
    actualCalorie = actualCalorie,
    email = email,
    age = age,
    username = username,
    height = height,
)

fun ActivityResponseItem.toDomain() = ActivityItem(
    difficulty = difficulty,
    imgUrl = imgUrl,
    caloriesBurned = caloriesBurned,
    movementList = movementList.map(MovementResponseItem::toDomain),
    movementCount = movementCount,
    id = id,
    cardColor = cardColor,
    type = type,
    category = category
)

fun MovementResponseItem.toDomain() = MovementItem(
    imgUrl = imgUrl,
    movementName = movementName,
    movementDesc = movementDesc,
    movementData = movementData.toDomain(),
)

fun BodyAngleResponse.toDomain() = BodyAngle(
    sudutKetiakKanan = sudutKetiakKanan,
    sudutKetiakKiri = sudutKetiakKiri,
    sudutLututKanan = sudutLututKanan,
    sudutLututKiri = sudutLututKiri,
    sudutPahaKanan = sudutPahaKanan,
    sudutPahaKiri = sudutPahaKiri,
    sudutPinggulKanan = sudutPinggulKanan,
    sudutPinggulKiri = sudutPinggulKiri,
    sudutPundakKanan = sudutPundakKanan,
    sudutPundakKiri = sudutPundakKiri,
    sudutSikuKanan = sudutSikuKanan,
    sudutSikuKiri = sudutSikuKiri,
    id = id
)

fun AddPointsResponseData.toDomain() = AddPoints(
    pointsAdded = pointsAdded,
    previousPoints = previousPoints,
    points = points
)

fun AddFoodsItem.toDomain() = FoodItem(
    portionSize = portionSize,
    id = id,
    foodName = foodName,
    calorie = calorie,
)

fun AddFoodsData.toDomain() = AddFoods(
    date = date,
    lastUpdated = lastUpdated,
    totalCarb = totalCarb,
    totalProtein = totalProtein,
    totalFat = totalFat,
    id = id,
    totalFiber = totalFiber,
    foods = foods.map(AddFoodsItem::toDomain)
)

fun FoodsHistoryResponseItem.toDomain() = FoodsHistoryItem(
    portionSize = portionSize,
    id = id,
    foodName = foodName,
    calorie = calorie
)

fun FoodHistoryDetailResponseItem.toDomain() = FoodHistoryDetailItem(
    date = date,
    lastUpdated = lastUpdated,
    totalCarb = totalCarb,
    totalProtein = totalProtein,
    totalFat = totalFat,
    id = id,
    totalFiber = totalFiber,
    foods = foods.map(FoodsHistoryResponseItem::toDomain)
)

fun AddEmotionDataResponse.toDomain() = Diary(
    id = id,
    date = date,
    emotionPositive = emotionPositive,
    emotionNegative = emotionNegative,
    emotionSource = emotionSource,
    lastUpdated = lastUpdated,
    note = note
)

fun DataDiary.toDomain() = Diary(
    date = date,
    lastUpdated = lastUpdated,
    note = note ,
    emotionSource =emotionSource,
    id = id,
    emotionPositive = emotionPositive,
    emotionNegative = emotionNegative
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

fun FeedDetailResponse.toDomain() = DetailFeed(
    link = link,
    title = title
)