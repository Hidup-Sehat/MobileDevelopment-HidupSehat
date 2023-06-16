package com.bangkit23.hidupsehat.domain.usecase.diary

import com.bangkit23.hidupsehat.domain.model.diary.Diary
import com.bangkit23.hidupsehat.domain.reporitory.DiaryRepository
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiaryInteractor @Inject constructor(private val diaryRepository: DiaryRepository) :
    DiaryUseCase {
    override fun addDiary(
        date: String,
        lastUpdated: String,
        note: String,
        emotionSource: String,
        emotionPositive: String,
        emotionNegative: String
    ): Flow<Result<Diary>> {
        return diaryRepository.addDiary(
            date = date,
            lastUpdated = lastUpdated,
            note = note,
            emotionSource = emotionSource,
            emotionPositive = emotionPositive,
            emotionNegative = emotionNegative
        )
    }

    override fun getDiaryByDate(date: String): Flow<Result<Diary>> {
        return diaryRepository.getDiaryByDate(date)
    }
}