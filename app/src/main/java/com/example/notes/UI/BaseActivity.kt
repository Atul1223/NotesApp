package com.example.notes.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.notes.R
import com.example.notes.ViewModels.BaseViewModel
import com.example.notes.ViewModels.LoginViewModel
import com.example.notes.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var baseViewModel : BaseViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModels()

        setNavController()

        initObservable()
    }

    private fun initObservable() {
        baseViewModel.getLoginState().observe(this) {
            updateUI(it)
        }
    }

    private fun initViewModels() {
        baseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
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