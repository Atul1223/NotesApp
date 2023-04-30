package com.example.notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.R
import com.example.notes.viewModels.BaseViewModel
import com.example.notes.viewModels.HomeViewModel
import com.example.notes.viewModels.LoginViewModel
import com.example.notes.databinding.ActivityBaseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BaseActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private var _binding: ActivityBaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavController()

        initObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initObservable() {
        loginViewModel.getLoginState().observe(this) {
            updateUI(it)
        }
    }

    private fun updateUI(loginState : Boolean) {
        if(loginState) {
            navController.navigate(R.id.homeFragment)
        }else{
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun setNavController() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}