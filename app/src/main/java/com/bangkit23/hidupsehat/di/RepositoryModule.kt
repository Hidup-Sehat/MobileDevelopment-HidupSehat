package com.bangkit23.hidupsehat.di

import com.bangkit23.hidupsehat.data.repository.FoodRepositoryImpl
import com.bangkit23.hidupsehat.domain.reporitory.FoodRepository
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
}