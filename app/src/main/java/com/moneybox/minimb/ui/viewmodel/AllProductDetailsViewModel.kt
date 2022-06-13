package com.moneybox.minimb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minimb.data.models.products.AllProductsResponse
import com.moneybox.minimb.domain.usecase.AllProductUseCase
import com.moneybox.minimb.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductDetailsViewModel @Inject constructor(
    private val allProductUseCase: AllProductUseCase
) : ViewModel() {
    private val _allProductResponse: MutableLiveData<Resource<AllProductsResponse>> =
        MutableLiveData()
    val allProductResponse: LiveData<Resource<AllProductsResponse>>
        get() = _allProductResponse

    fun getAllProductDetails(
        bearerToken: String
    ) = viewModelScope.launch {
        allProductUseCase.invoke(bearerToken).collect {
            _allProductResponse.postValue(it)
        }
    }
}