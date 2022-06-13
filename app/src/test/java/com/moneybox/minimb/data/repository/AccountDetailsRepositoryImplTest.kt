package com.moneybox.minimb.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moneybox.minimb.TestCoroutineRule
import com.moneybox.minimb.TestData
import com.moneybox.minimb.data.repository.dataSource.RemoteDataSource
import com.moneybox.minimb.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AccountDetailsRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutor: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @MockK
    lateinit var dataSource: RemoteDataSource

    private lateinit var repositoryImpl: AccountDetailsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repositoryImpl = AccountDetailsRepositoryImpl(dataSource)
    }

    @Test
    fun `should get successful LoginResponse once login is requested`() = runBlocking {

        val loginData = TestData.loginRequest
        val loginResponse = TestData.loginResponse
        val expectedResponse = flowOf(Resource.Success(loginResponse))

        coEvery { dataSource.getLoginResponse(loginData) } returns expectedResponse

        val response = repositoryImpl.getLoginResponse(loginData)

        assertEquals(response, expectedResponse)
    }

    @Test
    fun `should get successful AllProductDetails once requested with bearer token`() = runBlocking {

        val bearerToken = TestData.bearerTokenForProductDetails
        val allProductResponse = TestData.allProductsResponse
        val expectedResponse = flowOf(Resource.Success(allProductResponse))

        coEvery { dataSource.getAllProducts(bearerToken) } returns expectedResponse

        val response = repositoryImpl.getAllProducts(bearerToken)

        assertEquals(response, expectedResponse)
    }
}