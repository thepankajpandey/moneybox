package com.moneybox.minimb.data.repository.dataSource

import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.LoginResponse
import com.moneybox.minimb.data.models.products.AllProductsResponse
import com.moneybox.minimb.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getLoginResponse(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun getAllProducts(bearerToken: String): Flow<Resource<AllProductsResponse>>
}