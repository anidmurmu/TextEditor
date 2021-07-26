package com.ather.texteditor.presentation.editor

import androidx.lifecycle.MutableLiveData

class EditorViewState {
    val words: MutableLiveData<String> = MutableLiveData("")
    val wordCount: MutableLiveData<Int> = MutableLiveData(0)
}