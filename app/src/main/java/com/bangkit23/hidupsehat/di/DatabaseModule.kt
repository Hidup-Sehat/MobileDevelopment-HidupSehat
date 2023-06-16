package com.bangkit23.hidupsehat.di

import android.content.Context
import androidx.annotation.RawRes
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import com.bangkit23.hidupsehat.data.source.local.entity.ReminderEntity
import com.bangkit23.hidupsehat.data.source.local.room.FoodDao
import com.bangkit23.hidupsehat.data.source.local.room.HidupSehatDatabase
import com.bangkit23.hidupsehat.data.source.local.room.ReminderDao
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
        foodDaoProvider: Provider<FoodDao>,
        reminderDaoProvider: Provider<ReminderDao>,
    ): HidupSehatDatabase {
        return Room.databaseBuilder(
            context,
            HidupSehatDatabase::class.java,
            "hidup_sehat.db"
        ).fallbackToDestructiveMigration()
            .addCallback(DatabaseCallback(context, foodDaoProvider, reminderDaoProvider))
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(hidupSehatDatabase: HidupSehatDatabase): FoodDao =
        hidupSehatDatabase.foodDao()

    @Provides
    @Singleton
    fun provideReminderDao(hidupSehatDatabase: HidupSehatDatabase): ReminderDao =
        hidupSehatDatabase.reminderDao()
}

class DatabaseCallback(
    private val context: Context,
    private val foodDaoProvider: Provider<FoodDao>,
    private val reminderDaoProvider: Provider<ReminderDao>,
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingReminders(context, reminderDaoProvider.get())
            fillWithStartingFoods(context, foodDaoProvider.get())
        }
    }

    private fun loadJsonArray(context: Context, @RawRes rawFile: Int): JSONArray {
        val inputStream = context.resources.openRawResource(rawFile)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }

    private suspend fun fillWithStartingReminders(context: Context, reminderDao: ReminderDao) {
        try {
            val reminders = loadJsonArray(context, R.raw.reminders)
            for (i in 0 until  reminders.length()) {
                val item = reminders.getJSONObject(i)

                val id = item.getInt("id")
                val title = item.getString("title")
                val time = item.getLong("time")
                val type = item.getInt("type")
                val isActive = item.getBoolean("isActive")

                val reminderEntity = ReminderEntity(
                    id = id,
                    title = title,
                    time = time,
                    type = type,
                    isActive = isActive,
                )
                reminderDao.upsertReminder(reminderEntity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun fillWithStartingFoods(context: Context, foodDao: FoodDao) {
        try {
            val foods = loadJsonArray(context, R.raw.foods)
            for (i in 0 until  foods.length()) {
                val item = foods.getJSONObject(i)

                val id = item.getInt("id")
                val name = item?.getString("nama")
                val portionSize = item?.getString("ukuran_porsi")
                val energyKj = item?.getDouble("energi_kj")
                val energyKKal = item?.getDouble( "energi_kkal")
                val sugar = item?.getDouble("gula")
                val potassium = item?.getDouble("kalium")
                val carbohydrate = item?.getDouble("karbohidrat")
                val cholesterol = item?.getDouble("kolesterol")
                val fat = item?.getDouble("lemak")
                val saturatedFat = item?.getDouble("lemak_jenuh")
                val transFat = item?.getDouble("lemak_trans")
                val polyunsaturatedFat = item?.getDouble("lemak_tak_jenuh_ganda")
                val monounsaturatedFat = item?.getDouble("lemak_tak_jenuh_tunggal")
                val protein = item?.getDouble("protein")
                val fiber = item?.getDouble("serat")
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