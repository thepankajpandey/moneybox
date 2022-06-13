package com.moneybox.minimb.domain.usecase

import com.moneybox.minimb.data.repository.AccountDetailsRepository
import javax.inject.Inject

class AllProductUseCase @Inject constructor(private val repository: AccountDetailsRepository) {
    suspend fun invoke(
        bearerToken: String
    ) = repository.getAllProducts(bearerToken)
}