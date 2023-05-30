package com.bangkit23.hidupsehat.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import com.bangkit23.hidupsehat.data.source.local.room.FoodDao
import com.bangkit23.hidupsehat.data.source.local.room.HidupSehatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHidupSehatDatabase(
        @ApplicationContext context: Context,
        provider: Provider<FoodDao>,
    ): HidupSehatDatabase {
        return Room.databaseBuilder(
            context,
            HidupSehatDatabase::class.java,
            "hidup_sehat.db"
        ).fallbackToDestructiveMigration()
            .addCallback(FoodCallback(context, provider))
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(hidupSehatDatabase: HidupSehatDatabase): FoodDao =
        hidupSehatDatabase.foodDao()
}

class FoodCallback(
    private val context: Context,
    private val provider: Provider<FoodDao>,
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingFoods(context, provider.get())
        }
    }

    private fun loadJsonArray(context: Context): JSONArray {
        val inputStream = context.resources.openRawResource(R.raw.foods)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }

    private suspend fun fillWithStartingFoods(context: Context, foodDao: FoodDao) {
        try {
            val foods = loadJsonArray(context)
            for (i in 0 until  foods.length()) {
                val item = foods.getJSONObject(i)

                val id = item.getInt("id")
                val name = item?.getString("name")
                val portionSize = item?.getString("portion-size")
                val energyKj = item?.getDouble("energy-kj")
                val energyKKal = item?.getDouble( "energy-kkal")
                val sugar = item?.getDouble("sugar")
                val potassium = item?.getDouble("potassium")
                val carbohydrate = item?.getDouble("carbohydrate")
                val cholesterol = item?.getDouble("cholesterol")
                val fat = item?.getDouble("fat")
                val saturatedFat = item?.getDouble("saturated-fat")
                val transFat = item?.getDouble("trans-fat")
                val polyunsaturatedFat = item?.getDouble("polyunsaturated-fat")
                val monounsaturatedFat = item?.getDouble("monounsaturated-fat")
                val protein = item?.getDouble("protein")
                val fiber = item?.getDouble("fiber")
                val sodium = item?.getDouble("sodium")

                val foodEntity = FoodEntity(
                    id, name, portionSize, energyKj, energyKKal, sugar, potassium, carbohydrate, cholesterol,
                    fat, saturatedFat, transFat, polyunsaturatedFat, monounsaturatedFat, protein, fiber, sodium
                )
                foodDao.insertFood(foodEntity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}