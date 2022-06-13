package com.moneybox.minimb.di

import com.moneybox.minimb.data.api.APIService
import com.moneybox.minimb.data.repository.dataSource.RemoteDataSource
import com.moneybox.minimb.data.repository.dataSourceInteractor.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService): RemoteDataSource =
        RemoteDataSourceImpl(apiService)
}