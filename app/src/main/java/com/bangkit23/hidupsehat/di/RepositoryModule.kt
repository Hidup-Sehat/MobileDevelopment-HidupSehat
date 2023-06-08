package com.bangkit23.hidupsehat.di

import com.bangkit23.hidupsehat.data.repository.AuthRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.FeedRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.FoodRepositoryImpl
import com.bangkit23.hidupsehat.data.repository.UserRepositoryImpl
import com.bangkit23.hidupsehat.domain.reporitory.AuthRepository
import com.bangkit23.hidupsehat.domain.reporitory.FeedRepository
import com.bangkit23.hidupsehat.domain.reporitory.FoodRepository
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
    abstract fun provideFeedRepository(feedRepositoryImpl: FeedRepositoryImpl) : FeedRepository
}