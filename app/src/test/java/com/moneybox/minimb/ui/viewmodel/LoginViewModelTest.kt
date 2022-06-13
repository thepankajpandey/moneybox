package com.moneybox.minimb.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moneybox.minimb.TestCoroutineRule
import com.moneybox.minimb.TestData
import com.moneybox.minimb.domain.usecase.LoginUseCase
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
class LoginViewModelTest {

    @get:Rule
    val testInstantTaskExecutor: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRle = TestCoroutineRule()

    private lateinit var viewModel: LoginViewModel

    @MockK
    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = LoginViewModel(loginUseCase)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get Loginresponse once api call`() {

        testCoroutineRle.runBlockingTest {
            val loginRequest = TestData.loginRequest
            val loginResponse = TestData.loginResponse
            val expectedResponse = Resource.Success(loginResponse)

            val flow = flow {
                emit(expectedResponse)
            }

            coEvery { loginUseCase.invoke(loginRequest) } returns flow
            viewModel.getLoginResponse(loginRequest)

            assertEquals(viewModel.loginResponse.value, expectedResponse)
        }
    }
}