package com.moneybox.minimb.di

import com.moneybox.minimb.data.repository.AccountDetailsRepository
import com.moneybox.minimb.data.repository.AccountDetailsRepositoryImpl
import com.moneybox.minimb.data.repository.dataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAccountDetailsRepository(remoteDataSource: RemoteDataSource): AccountDetailsRepository {
        return AccountDetailsRepositoryImpl(remoteDataSource)
    }
}