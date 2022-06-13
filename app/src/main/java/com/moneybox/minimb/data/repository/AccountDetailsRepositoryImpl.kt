package com.moneybox.minimb.data.repository

import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.LoginResponse
import com.moneybox.minimb.data.models.products.AllProductsResponse
import com.moneybox.minimb.data.repository.dataSource.RemoteDataSource
import com.moneybox.minimb.utils.Resource
import kotlinx.coroutines.flow.Flow

class AccountDetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    AccountDetailsRepository {

    override suspend fun getLoginResponse(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return remoteDataSource.getLoginResponse(loginRequest)
    }

    override suspend fun getAllProducts(
        bearerToken: String
    ): Flow<Resource<AllProductsResponse>> {
        return remoteDataSource.getAllProducts(bearerToken)
    }
}