package com.bangkit23.hidupsehat.di

import com.bangkit23.hidupsehat.domain.usecase.food.FoodInteractor
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
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
}