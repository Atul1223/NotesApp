package com.example.notes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.notesData.Notes
import com.example.notes.notesData.NotesDao
import com.example.notes.utils.AppConstants
import com.example.notes.viewModels.NotesViewModelFactory
import com.example.notes.viewModels.NoteViewModel
import org.koin.java.KoinJavaComponent

class NoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by activityViewModels {
        NotesViewModelFactory(getMyParameterFromKoin())
    }

    private fun getMyParameterFromKoin(): NotesDao {
        return KoinJavaComponent.getKoin().get()
    }

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentTAG: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservable()

        initClickListeners()
    }

    private fun initObservable() {

        noteViewModel.getCurrentTagType().observe(viewLifecycleOwner) {
            it?.let {
                updateTagUi(it)
                Log.d("debug-- ","$it")
            }
        }

        noteViewModel.getIsNewNote().observe(viewLifecycleOwner) {
            it?.let {
                setNoteContent(it)
            }
        }
    }

    private fun initClickListeners() {

        binding.vScreener.setOnClickListener {
            openCloseTagMenu()
        }

        binding.ivBtnBackNote.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        binding.ivNoteTag.setOnClickListener {
            openCloseTagMenu()
        }

        binding.vLayoutTagSelector.vTag1.setOnClickListener {
            updateNoteVM(R.drawable.vd_tag_1)
        }

        binding.vLayoutTagSelector.vTag2.setOnClickListener {
            updateNoteVM(R.drawable.vd_tag_2)
        }

        binding.vLayoutTagSelector.vTag3.setOnClickListener {
            updateNoteVM(R.drawable.vd_tag_3)
        }

        binding.vLayoutTagSelector.vTag4.setOnClickListener {
            updateNoteVM(R.drawable.vd_tag_4)
        }

        binding.vLayoutTagSelector.vTag5.setOnClickListener {
            updateNoteVM(R.drawable.vd_tag_5)
        }

        binding.ivBtnSaveNote.setOnClickListener {
            if(validateFields()){
                noteViewModel.saveNotesData(
                    returnNote()
                )
                binding.ivBtnBackNote.performClick()
            }
            else{
                Toast.makeText(requireContext(),"Note heading is Empty",Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBtnDeleteNote.setOnClickListener {

        }
    }

    private fun openCloseTagMenu() {
        binding.vLayoutTagSelector.root.isVisible = !binding.vLayoutTagSelector.root.isVisible
        binding.vScreener.isVisible = !binding.vScreener.isVisible
    }

    private fun updateTagUi(resId: Int){

        binding.ivNoteTag.setImageResource(resId)

        when(resId) {
            R.drawable.vd_tag_1 -> {
                updateTagMenuVisibility(tag1 = false)
                updateTitleColor(R.color.color_tag1)
                currentTAG = AppConstants.NoteTags.tag_1
            }

            R.drawable.vd_tag_2 -> {
                updateTagMenuVisibility(tag2 = false)
                updateTitleColor(R.color.color_tag2)
                currentTAG = AppConstants.NoteTags.tag_2
            }

            R.drawable.vd_tag_3 -> {
                updateTagMenuVisibility(tag3 = false)
                updateTitleColor(R.color.color_tag3)
                currentTAG = AppConstants.NoteTags.tag_3
            }

            R.drawable.vd_tag_4 -> {
                updateTagMenuVisibility(tag4 = false)
                updateTitleColor(R.color.color_tag4)
                currentTAG = AppConstants.NoteTags.tag_4
            }

            R.drawable.vd_tag_5 -> {
                updateTagMenuVisibility(tag5 = false)
                updateTitleColor(R.color.color_tag5)
                currentTAG = AppConstants.NoteTags.tag_5
            }
        }

    }

    private fun updateTagMenuVisibility(
        tag1: Boolean = true,
        tag2: Boolean = true,
        tag3: Boolean = true,
        tag4: Boolean = true,
        tag5: Boolean = true
    ) {
        binding.vLayoutTagSelector.vTag1.isVisible = tag1
        binding.vLayoutTagSelector.vTag2.isVisible = tag2
        binding.vLayoutTagSelector.vTag3.isVisible = tag3
        binding.vLayoutTagSelector.vTag4.isVisible = tag4
        binding.vLayoutTagSelector.vTag5.isVisible = tag5
    }

    private fun updateTitleColor(colorId: Int) {
        binding.tvNoteHeading.setTextColor(ContextCompat.getColor(requireContext(),colorId))
    }

    private fun updateNoteVM(resId: Int) {
        noteViewModel.setCurrentTagType(resID = resId)
    }

    private fun setNoteContent(isNewNote: Boolean) {
        binding.ivBtnDeleteNote.isVisible = !isNewNote

        Log.d("debug-- 2","${noteViewModel.getIsNewNote().value}")

        if(isNewNote) {
//            binding.tvNoteHeading.text  =
//
//            binding.tvNoteContent.text =
//
//            updateNoteVM() =
        }
    }

    private fun returnNote(): Notes {
        return Notes(
            heading = binding.tvNoteHeading.text.toString(),
            content = binding.tvNoteContent.text.toString(),
            tag = currentTAG
        )
    }

    private fun validateFields(): Boolean {
        if(binding.tvNoteHeading.text.isNullOrBlank() || binding.tvNoteHeading.text.isNullOrEmpty()) {
            return false
        }
        return  true
    }
}