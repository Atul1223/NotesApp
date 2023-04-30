package com.example.notes.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.notes.R
import com.example.notes.viewModels.HomeViewModel
import com.example.notes.viewModels.LoginViewModel
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.viewModels.NoteViewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val noteViewModel: NoteViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservable()

        initClickListeners()

        updateImage()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        updateImage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservable() {
        homeViewModel.getNotesListSize().observe(viewLifecycleOwner){
            binding.ivHomeVd.isVisible = it <= 0
        }
    }

    private fun initClickListeners() {
        binding.ivCreateNote.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_noteFragment)
            noteViewModel.setIsNewNote(true)
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

    private fun updateImage() {
       when(homeViewModel.randomNumGenerator()){
           1 -> binding.ivHomeVd.setImageResource(R.drawable.vd_stickman_one)

           2 -> binding.ivHomeVd.setImageResource(R.drawable.vd_stickman_two)

           3 -> binding.ivHomeVd.setImageResource(R.drawable.vd_stickman_three)

           4 -> binding.ivHomeVd.setImageResource(R.drawable.vd_stickman_four)

           5 -> binding.ivHomeVd.setImageResource(R.drawable.vd_stickman_five)
       }
    }

    private fun handleSearch() {
        if(binding.tvHomeSearch.isInvisible){
            binding.tvHomeSearch.isVisible = true
            updateSearchUI()
        }
    }

    private fun handleCloseSearch() {
        binding.tvHomeSearch.text?.clear()
        binding.tvHomeSearch.isInvisible = true
        updateSearchUI()
    }

    private fun updateSearchUI() {
        binding.ivHomeLogout.isVisible = !binding.ivHomeLogout.isVisible
        binding.ivHomeSearchClose.isVisible = !binding.ivHomeSearchClose.isVisible
        binding.tvHomeWelcome.isVisible = !binding.tvHomeWelcome.isVisible
        binding.tvHomeUsername.isVisible = !binding.tvHomeUsername.isVisible
    }

}