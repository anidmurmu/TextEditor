package com.ather.texteditor.presentation.editor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ather.texteditor.R
import com.ather.texteditor.databinding.FragmentEditorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorFragment : Fragment() {

    private val model: EditorViewModel by viewModels()
    private lateinit var binding: FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentEditorBinding>(
            inflater,
            R.layout.fragment_editor,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.viewState.value?.words?.observe(requireActivity(), {
            Log.d("apple", it)
        })

        model.viewEvent.undoEvent.observe(requireActivity(), { event ->
            event?.getContentIfNotHandledOrReturnNull()?.let {
                if (it) {
                    Toast.makeText(requireContext(), "this is toast", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}