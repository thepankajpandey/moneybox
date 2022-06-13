package com.moneybox.minimb

import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.LoginResponse
import com.moneybox.minimb.data.models.login.SessionDataResponse
import com.moneybox.minimb.data.models.login.UserResponse
import com.moneybox.minimb.data.models.products.AllProductsResponse
import com.moneybox.minimb.data.models.products.ProductResponse

object TestData {
    private const val bearerToken = "9SJZjYd13QUe/JlmW9OI2RR3qRHRyGlfNn14b6OT4Pc="
    private const val userId = "947002e4-4b6a-43ff-9f3d-0d939ef2dbaf"
    private const val firstName = "Jaeren"
    private const val lastName = "Coathup"
    private const val email = "jaeren+androidtest@moneyboxapp.com"
    private const val password = "P455word12"
    private val session = SessionDataResponse(bearerToken)
    private val user = UserResponse(userId, firstName, lastName, email)
    val loginRequest = LoginRequest("", "", "", email, password)
    val loginResponse = LoginResponse(session, user)


    val bearerTokenForProductDetails = "Bearer $bearerToken"
    private const val totalPlanValue = 2000.0f
    private const val totalEarnings = 105.5f
    private const val totalEarningsAsPercentage = 5.22f
    private val products = emptyList<ProductResponse>()
    val allProductsResponse = AllProductsResponse(
        totalPlanValue, totalEarnings,
        totalEarningsAsPercentage, products
    )
}