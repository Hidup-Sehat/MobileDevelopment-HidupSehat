package com.bangkit23.hidupsehat.di

import com.bangkit23.hidupsehat.data.repository.ActivityRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.AuthRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.DiaryRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.FeedRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.FoodRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.LeaderboardRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.MonitoringRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.ReminderRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.UserRepositoryImpl
import com.bangkit23.hidupsehat.domain.reporitory.ActivityRepository
import com.bangkit23.hidupsehat.domain.reporitory.AuthRepository
import com.bangkit23.hidupsehat.domain.reporitory.DiaryRepository
import com.bangkit23.hidupsehat.domain.reporitory.FeedRepository
import com.bangkit23.hidupsehat.domain.reporitory.FoodRepository
import com.bangkit23.hidupsehat.domain.reporitory.LeaderboardRepository
import com.bangkit23.hidupsehat.domain.reporitory.MonitoringRepository
import com.bangkit23.hidupsehat.domain.reporitory.ReminderRepository
import com.bangkit23.hidupsehat.domain.reporitory.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideReminderRepository(reminderRepositoryImpl: ReminderRepositoryImpl): ReminderRepository

    @Binds
    @Singleton
    abstract fun provideLeaderboardRepository(leaderboardRepositoryImpl: LeaderboardRepositoryImpl): LeaderboardRepository

    @Binds
    @Singleton
    abstract fun provideActivityRepository(activityRepositoryImpl: ActivityRepositoryImpl): ActivityRepository

    @Binds
    @Singleton
    abstract fun provideMonitoringRepository(monitoringRepositoryImpl: MonitoringRepositoryImpl): MonitoringRepository

    @Binds
    @Singleton
    abstract fun provideFeedRepository(feedRepositoryImpl: FeedRepositoryImpl) : FeedRepository

    @Binds
    @Singleton
    abstract fun provideDiaryRepository(diaryRepositoryImpl: DiaryRepositoryImpl) : DiaryRepository
}