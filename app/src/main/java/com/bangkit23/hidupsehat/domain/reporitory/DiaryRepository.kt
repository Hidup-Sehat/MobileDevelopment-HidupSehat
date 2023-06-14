package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.domain.model.diary.Diary
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun addDiary(
        date: String,
        lastUpdated: String,
        note: String,
        emotionSource: String,
        emotionPositive: String,
        emotionNegative: String
    ): Flow<Result<Diary>>

}