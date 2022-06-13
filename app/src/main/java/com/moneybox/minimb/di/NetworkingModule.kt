package com.moneybox.minimb.di

import com.moneybox.minimb.BuildConfig
import com.moneybox.minimb.data.api.APIService
import com.moneybox.minimb.data.networking.NoAuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun provideNoAuthenticatorInterceptor(): NoAuthenticationInterceptor {
        return NoAuthenticationInterceptor()
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: NoAuthenticationInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .connectTimeout(180, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}