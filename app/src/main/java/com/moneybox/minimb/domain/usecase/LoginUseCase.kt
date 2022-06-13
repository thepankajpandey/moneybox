package com.moneybox.minimb.domain.usecase

import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.repository.AccountDetailsRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AccountDetailsRepository) {
    suspend fun invoke(loginRequest: LoginRequest) = repository.getLoginResponse(loginRequest)
}