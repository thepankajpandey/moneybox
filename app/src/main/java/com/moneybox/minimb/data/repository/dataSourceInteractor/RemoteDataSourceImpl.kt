package com.moneybox.minimb.data.repository.dataSourceInteractor

import com.moneybox.minimb.data.api.APIService
import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.LoginResponse
import com.moneybox.minimb.data.models.products.AllProductsResponse
import com.moneybox.minimb.data.repository.dataSource.RemoteDataSource
import com.moneybox.minimb.utils.Resource
import com.moneybox.minimb.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSourceImpl(
    private val apiService: APIService
) : RemoteDataSource {
    override suspend fun getLoginResponse(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            val reposnse = safeApiCall(Dispatchers.IO) {
                apiService.getLoginResponse(loginRequest)
            }
            emit(reposnse)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllProducts(
        bearerToken: String
    ): Flow<Resource<AllProductsResponse>> {
        return flow {
            val reposnse = safeApiCall(Dispatchers.IO) {
                apiService.getAllProducts(bearerToken)
            }
            emit(reposnse)
        }.flowOn(Dispatchers.IO)
    }
}