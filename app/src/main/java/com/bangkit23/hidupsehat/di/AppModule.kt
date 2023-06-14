package com.bangkit23.hidupsehat.di

import com.bangkit23.hidupsehat.domain.usecase.activity.ActivityInteractor
import com.bangkit23.hidupsehat.domain.usecase.activity.ActivityUseCase
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthInteractor
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import com.bangkit23.hidupsehat.domain.usecase.diary.DiaryInteractor
import com.bangkit23.hidupsehat.domain.usecase.diary.DiaryUseCase
import com.bangkit23.hidupsehat.domain.usecase.feed.FeedInteractor
import com.bangkit23.hidupsehat.domain.usecase.feed.FeedUseCase
import com.bangkit23.hidupsehat.domain.usecase.food.FoodInteractor
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import com.bangkit23.hidupsehat.domain.usecase.leaderboard.LeaderboardInteractor
import com.bangkit23.hidupsehat.domain.usecase.leaderboard.LeaderboardUseCase
import com.bangkit23.hidupsehat.domain.usecase.reminder.ReminderInteractor
import com.bangkit23.hidupsehat.domain.usecase.reminder.ReminderUseCase
import com.bangkit23.hidupsehat.domain.usecase.user.UserInteractor
import com.bangkit23.hidupsehat.domain.usecase.user.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideFoodUseCase(foodInteractor: FoodInteractor): FoodUseCase

    @Binds
    @Singleton
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase

    @Binds
    @Singleton
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase

    @Binds
    @Singleton
    abstract fun provideFeedUseCase(feedInteractor: FeedInteractor) : FeedUseCase

    @Binds
    @Singleton
    abstract fun provideReminderUseCase(reminderInteractor: ReminderInteractor): ReminderUseCase

    @Binds
    @Singleton
    abstract fun provideLeaderboardUseCase(leaderboardInteractor: LeaderboardInteractor): LeaderboardUseCase

    @Binds
    @Singleton
    abstract fun provideActivityUseCase(activityInteractor: ActivityInteractor): ActivityUseCase

    @Binds
    @Singleton
    abstract fun provideDiaryUseCase(diaryInteractor: DiaryInteractor) : DiaryUseCase
}