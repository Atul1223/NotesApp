package com.example.notes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.notesData.Notes
import com.example.notes.notesData.NotesDao
import com.example.notes.notesData.NotesDatabase
import com.example.notes.notesData.NotesViewModelFactory
import com.example.notes.viewModels.NoteViewModel
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent
import java.util.Date

class NoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels {
        NotesViewModelFactory(getMyParameterFromKoin())
    }

    private fun getMyParameterFromKoin(): NotesDao {
        return KoinJavaComponent.getKoin().get()
    }

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

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
            noteViewModel.saveNotesData(
                Notes(
                    heading = "Hello",
                    content = "Hello world",
                    tag = noteViewModel.getCurrentTagType().toString(),
                    //date = Date()
                )
            )
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
            }

            R.drawable.vd_tag_2 -> {
                updateTagMenuVisibility(tag2 = false)
                updateTitleColor(R.color.color_tag2)
            }

            R.drawable.vd_tag_3 -> {
                updateTagMenuVisibility(tag3 = false)
                updateTitleColor(R.color.color_tag3)
            }

            R.drawable.vd_tag_4 -> {
                updateTagMenuVisibility(tag4 = false)
                updateTitleColor(R.color.color_tag4)
            }

            R.drawable.vd_tag_5 -> {
                updateTagMenuVisibility(tag5 = false)
                updateTitleColor(R.color.color_tag5)
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

        if(isNewNote) {
//            binding.tvNoteHeading.text  =
//
//            binding.tvNoteContent.text =
//
//            updateNoteVM() =
        }
    }
}