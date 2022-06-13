package com.moneybox.minimb.ui.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.moneybox.minimb.R
import com.moneybox.minimb.data.models.login.LoginRequest
import com.moneybox.minimb.data.models.login.SessionDataResponse
import com.moneybox.minimb.databinding.ActivityLoginBinding
import com.moneybox.minimb.ui.features.myplan.ProductsDetailActivity
import com.moneybox.minimb.ui.viewmodel.LoginViewModel
import com.moneybox.minimb.utils.Constants
import com.moneybox.minimb.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginRequest: LoginRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputEmail.doAfterTextChanged {
            emailValidator()
        }
        binding.inputPassword.doAfterTextChanged {
            passwordValidator()
        }

        binding.btnLogin.setOnClickListener {
            onClick()
        }
    }

    private fun emailValidator(): Boolean {
        val email = binding.inputEmail.text.toString().trim()

        return if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputEmail.error = null
            true
        } else {
            binding.inputEmail.error = getString(R.string.text_incorrect_email)
            false
        }
    }

    private fun passwordValidator(): Boolean {
        val password = binding.inputPassword.text.toString().trim()
        return if (password.isNotEmpty()) {
            binding.inputPassword.error = null
            true
        } else {
            binding.inputPassword.error = getString(R.string.text_incorrect_password)
            false
        }
    }

    private fun onClick() {
        if (emailValidator() && passwordValidator()) {
            binding.progressBar.visibility = View.VISIBLE
            loginRequest = LoginRequest(
                "",
                "",
                "",
                binding.inputEmail.text.toString().trim(),
                binding.inputPassword.text.toString().trim()
            )
            getLoginResponse(loginRequest)
        }
    }

    private fun getLoginResponse(loginRequest: LoginRequest) {
        viewModel.getLoginResponse(loginRequest)
        viewModel.loginResponse.observe(this) { response ->
            binding.progressBar.visibility = View.GONE
            when (response) {

                is Resource.Success -> {
                    Log.d("Success: ", response.data.toString())
                    val result: SessionDataResponse? = response.data?.session
                    if (result != null) {
                        startActivityWithIntent(result)
                    }
                }
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Toast.makeText(this, getString(R.string.text_login_error), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    response.data.toString().let { Log.d("Error: ", it) }
                }
            }
        }
    }

    private fun startActivityWithIntent(sessionDataResponse: SessionDataResponse) {
        val intent = Intent(this, ProductsDetailActivity::class.java)
        intent.putExtra(Constants.ARGS_SESSION_DATA, sessionDataResponse.bearerToken.trim())
        startActivity(intent)
    }
}