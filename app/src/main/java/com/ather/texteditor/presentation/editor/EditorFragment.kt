package com.ather.texteditor.presentation.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ather.texteditor.R
import com.ather.texteditor.base.KeyPadListener
import com.ather.texteditor.base.setKeypadListener
import com.ather.texteditor.databinding.FragmentEditorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_editor.*


@AndroidEntryPoint
class EditorFragment : Fragment() {

    private val model: EditorViewModel by viewModels()
    private lateinit var binding: FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
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
        clRootView.setKeypadListener(object : KeyPadListener {
            override fun onKeypadShown() {
                etEditor.requestFocus()
                model.disableUndo()
            }

            override fun onKeypadHidden() {
                etEditor.clearFocus()
                model.enableUndo()
                val currentWord = model.getCurrentWord()
                val wordCount = model.getWordCount(currentWord)
                model.updateWordCount(wordCount)
                val wordCountPair = Pair(currentWord, wordCount)
                model.saveWordCountToStack(wordCountPair)
            }
        })
    }


}