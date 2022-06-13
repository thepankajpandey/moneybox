package com.moneybox.minimb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.LoginResponse
import com.moneybox.minimb.domain.usecase.LoginUseCase
import com.moneybox.minimb.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun getLoginResponse(loginRequest: LoginRequest) = viewModelScope.launch {
        loginUseCase.invoke(loginRequest).collect {
            _loginResponse.postValue(it)
        }
    }
}