package com.bangkit23.hidupsehat.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodEntity: FoodEntity)

    @Query("SELECT * FROM food_entity WHERE name = :foodName LIMIT 1")
    fun getFoodByName(foodName: String): Flow<FoodEntity>

    @Query("SELECT * FROM food_entity WHERE name LIKE :query || '%' LIMIT 20")
    fun searchFoods(query: String): Flow<List<FoodEntity>>

    @Query("SELECT * FROM food_entity WHERE name = :foodName")
    fun getFoodsPortionSize(foodName: String): Flow<List<FoodEntity>>
}