package com.moneybox.minimb.data.api

import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.LoginResponse
import com.moneybox.minimb.data.models.products.AllProductsResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("users/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequest): LoginResponse

    @GET("investorproducts")
    suspend fun getAllProducts(
        @Header("Authorization") bearerToken: String
    ): AllProductsResponse
}