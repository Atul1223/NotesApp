package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentSettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {

        binding.ivBtnBackSettings.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
        }

        binding.vSettingsNoteSync.setOnClickListener {
            binding.btnAutoSyncToggle.isChecked = !binding.btnAutoSyncToggle.isChecked
        }

        binding.ivSettingDeleteAll.setOnClickListener {

        }

        binding.vSettingsNoteDelete.setOnClickListener {
            binding.ivSettingDeleteAll.performClick()
            pressView(binding.ivSettingDeleteAll)
        }

        binding.tvUserEmail.setOnClickListener {
            pressView(binding.ivUser)
        }

        binding.btnLogout.setOnClickListener {
            pressView(binding.ivLogout)
        }
    }

    private fun pressView(view: View) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            view.isPressed = true
            delay(100)
            view.isPressed = false
        }
    }
}