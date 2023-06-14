package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.data.source.local.room.FoodDao
import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.data.source.remote.request.AddFoodsRequest
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.reporitory.FoodRepository
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val remoteDataSource: RemoteDataSource,
    private val foodDao: FoodDao,
) : FoodRepository {

    override fun getFoodByName(foodName: String) = flow {
        try {
            val food = foodDao.getFoodByName(foodName).map {
                it.toDomain()
            }
            emitAll(food)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override fun searchFoods(query: String) = flow {
        val foods = foodDao.searchFoods(query).map {
            it.toDomain()
        }
        emitAll(foods)
    }.flowOn(Dispatchers.IO)

    override fun getFoodsPortionSize(foodName: String) = flow {
        try {
            val foods = foodDao.getFoodsPortionSize(foodName).map {
                it.toDomain()
            }
            emitAll(foods)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllFoods(): Flow<List<Food>> = flow {
        try {
            val foods = foodDao.getAllFood().map {
                it.toDomain()
            }
            emitAll(foods)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)


    override fun saveFoods(addFoodsRequest: AddFoodsRequest) = flow {
        emit(Result.Loading())
        try {
            val userId = firebaseAuth.getSignedUser()?.userId
            val response = remoteDataSource.saveFoods(userId.toString(), addFoodsRequest)
            emit(Result.Success(response.data.toDomain()))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}