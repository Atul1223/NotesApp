package com.example.notes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.viewModels.HomeViewModel
import com.example.notes.viewModels.LoginViewModel
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.notesData.NotesDao
import com.example.notes.viewModels.NotesViewModelFactory
import com.example.notes.viewModels.NoteViewModel
import org.koin.java.KoinJavaComponent.getKoin

class HomeFragment : Fragment(), NotesAdapter.OnItemClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val noteViewModel: NoteViewModel by activityViewModels {
        NotesViewModelFactory(getMyParameterFromKoin())
    }

    private fun getMyParameterFromKoin(): NotesDao {
        return getKoin().get()
    }

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
            it?.let {
                if(it<=0) {
                    binding.ivHomeVd.isVisible = true
                    binding.notesRecyclerView.isVisible =  false
                }
                else{
                    binding.ivHomeVd.isVisible = false
                    binding.notesRecyclerView.isVisible = true
                    initRecyclerView()
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.notesRecyclerView.adapter =  NotesAdapter(this)
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    private fun initClickListeners() {
        binding.ivCreateNote.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
            noteViewModel.setIsNewNote(true)
            homeViewModel.addToNotesList()
        }

        binding.ivHomeSearch.setOnClickListener {
            handleSearch()
        }

        binding.ivHomeSearchClose.setOnClickListener {
            handleCloseSearch()
            homeViewModel.removeFromNotesList()
        }

        binding.ivHomeSettings.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }

        binding.ivHomeSync.setOnClickListener {
            binding.vHomeSyncAnimation.progress = 0f
            binding.vHomeSyncAnimation.playAnimation()
            binding.vHomeSyncAnimation.loop(true)
            binding.ivHomeSync.isVisible = false
            binding.vHomeSyncAnimation.isVisible= true
        }

        binding.vHomeSyncAnimation.setOnClickListener {
            binding.vHomeSyncAnimation.pauseAnimation()
            binding.ivHomeSync.isVisible = true
            binding.vHomeSyncAnimation.isVisible= false
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
        binding.ivHomeSettings.isVisible = !binding.ivHomeSettings.isVisible
        binding.ivHomeSearchClose.isVisible = !binding.ivHomeSearchClose.isVisible
        binding.vHomeAnimation.isVisible = !binding.vHomeAnimation.isVisible
        //binding.tvHomeWelcome.isVisible = !binding.tvHomeWelcome.isVisible
        //binding.tvHomeUsername.isVisible = !binding.tvHomeUsername.isVisible
    }

    override fun onItemClick(position: Int) {
        binding.root.findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
        noteViewModel.setIsNewNote(false)
        Toast.makeText(requireContext(),"$position",Toast.LENGTH_SHORT).show()
        Log.d("debug-- 1","${noteViewModel.getIsNewNote().value}")
    }

    override fun onDeleteClick(position: Int) {

    }

}