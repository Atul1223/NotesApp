package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.notes.viewModels.BaseViewModel
import com.example.notes.viewModels.LoginViewModel
import com.example.notes.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        initObservable()

        initClickListeners()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservable() {
        loginViewModel.getLoginState().observe(viewLifecycleOwner){
            if(it){

            }
        }
    }

    private fun initClickListeners() {
        binding.btnLogin.setOnClickListener{
            loginViewModel.loginUser()
        }
    }
}