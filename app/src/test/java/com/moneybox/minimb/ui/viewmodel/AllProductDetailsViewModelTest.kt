package com.moneybox.minimb.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moneybox.minimb.TestCoroutineRule
import com.moneybox.minimb.TestData
import com.moneybox.minimb.domain.usecase.AllProductUseCase
import com.moneybox.minimb.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AllProductDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutor: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRle = TestCoroutineRule()

    private lateinit var viewModel: AllProductDetailsViewModel

    @MockK
    lateinit var productUseCase: AllProductUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = AllProductDetailsViewModel(productUseCase)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get AllProductDetails once api call made`() {

        testCoroutineRle.runBlockingTest {
            val bearerToken = TestData.bearerTokenForProductDetails
            val response = TestData.allProductsResponse
            val expectedResponse = Resource.Success(response)

            val flow = flow {
                emit(expectedResponse)
            }

            coEvery { productUseCase.invoke(bearerToken) } returns flow
            viewModel.getAllProductDetails(bearerToken)

            assertEquals(viewModel.allProductResponse.value, expectedResponse)
        }
    }
}