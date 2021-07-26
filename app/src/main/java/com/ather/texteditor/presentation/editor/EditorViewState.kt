package com.ather.texteditor.presentation.editor

import androidx.lifecycle.MutableLiveData
import java.util.*

class EditorViewState {
    val words: MutableLiveData<String> = MutableLiveData("")
    val wordCount: MutableLiveData<String> = MutableLiveData("Word Count : 0")
    val wordStack: Stack<Pair<String, Int>> = Stack()
}