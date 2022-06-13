package com.moneybox.minimb.ui.features.myplan

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.moneybox.minimb.R
import com.moneybox.minimb.databinding.ActivityProductsDetailBinding
import com.moneybox.minimb.ui.viewmodel.AllProductDetailsViewModel
import com.moneybox.minimb.utils.Constants
import com.moneybox.minimb.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsDetailActivity : AppCompatActivity() {

    private val viewModel: AllProductDetailsViewModel by viewModels()
    private lateinit var binding: ActivityProductsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productProgressBar.visibility = View.VISIBLE
        val headerData = intent.getStringExtra(Constants.ARGS_SESSION_DATA)
        if (headerData != null) {
            getLoginResponse(headerData)
        }
    }


    private fun getLoginResponse(bearerToken: String) {
        val finalToken = "Bearer $bearerToken"
        viewModel.getAllProductDetails(
            finalToken
        )
        viewModel.allProductResponse.observe(this) { response ->
            binding.productProgressBar.visibility = View.GONE
            when (response) {
                is Resource.Success -> {
                    val result = response.data
                    result.toString().let { Log.d("Success: ", it) }
                    val planValue = "£ ${result?.totalPlanValue}"
                    binding.ivPlanValue.text = planValue
                }
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Toast.makeText(this, getString(R.string.text_error), Toast.LENGTH_SHORT)
                        .show()
                    response.data.toString().let { Log.d("Error: ", it) }
                }
                else -> {
                    response.data.toString().let { Log.d("Error: ", it) }
                }
            }
        }
    }
}