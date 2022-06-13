package com.moneybox.minimb.di

import com.moneybox.minimb.data.repository.AccountDetailsRepository
import com.moneybox.minimb.domain.usecase.AllProductUseCase
import com.moneybox.minimb.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideAllProductUseCase(repository: AccountDetailsRepository): AllProductUseCase {
        return AllProductUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: AccountDetailsRepository): LoginUseCase {
        return LoginUseCase(repository)
    }
}