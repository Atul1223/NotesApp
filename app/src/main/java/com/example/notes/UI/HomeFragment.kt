package com.example.notes.UI

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.notes.ViewModels.BaseViewModel
import com.example.notes.ViewModels.HomeViewModel
import com.example.notes.ViewModels.LoginViewModel
import com.example.notes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val baseViewModel: BaseViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

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
            if(!it){
                baseViewModel.updateLoginState()
            }
        }

        homeViewModel.getNotesListSize().observe(viewLifecycleOwner){
            if(it>0){
                updateAnimation(false)
            }
            else{
                updateAnimation(true)
            }
        }
    }

    private fun initClickListeners() {
        binding.ivCreateNote.setOnClickListener {
            loginViewModel.logOutUser()
        }

        binding.ivHomeAnimation.setOnClickListener {
            homeViewModel.addToNotesList()
        }

        binding.ivHomeSearch.setOnClickListener {
            handleSearch()
        }

        binding.ivHomeSearchClose.setOnClickListener {
            handleCloseSearch()
        }

        binding.ivHomeLogout.setOnClickListener {
            loginViewModel.logOutUser()
        }
    }

    private fun updateAnimation(start: Boolean) {
        if(start){
            binding.ivHomeAnimation.isVisible = true
            (binding.ivHomeAnimation.drawable as AnimationDrawable).start()
        }
        else{
            (binding.ivHomeAnimation.drawable as AnimationDrawable).stop()
            binding.ivHomeAnimation.isVisible = false
        }
    }

    private fun handleSearch() {
        if(binding.tvHomeSearch.isInvisible){
            binding.tvHomeSearch.isVisible = true
            updateWelcomeTextUI()
            updateLogoutUI()
            updateCloseSearchUI()
        }
    }

    private fun handleCloseSearch() {
        binding.tvHomeSearch.text?.clear()
        binding.tvHomeSearch.isInvisible = true
        updateWelcomeTextUI()
        updateLogoutUI()
        updateCloseSearchUI()
    }

    private fun updateLogoutUI() {
        binding.ivHomeLogout.isVisible = !binding.ivHomeLogout.isVisible
    }

    private fun updateCloseSearchUI() {
        binding.ivHomeSearchClose.isVisible = !binding.ivHomeSearchClose.isVisible
    }

    private fun updateWelcomeTextUI() {
        binding.tvHomeWelcome.isVisible = !binding.tvHomeWelcome.isVisible
        binding.tvHomeUsername.isVisible = !binding.tvHomeUsername.isVisible
    }

}