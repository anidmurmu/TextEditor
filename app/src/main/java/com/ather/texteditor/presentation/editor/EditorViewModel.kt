package com.ather.texteditor.presentation.editor

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ather.texteditor.R
import com.ather.texteditor.base.event.Event
import com.ather.texteditor.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EditorViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    private val _viewState = MutableLiveData<EditorViewState>()
    val viewState: LiveData<EditorViewState> = _viewState

    private val _viewEvent = EditorEvent()

    init {
        _viewState.value = EditorViewState()
    }

    fun updateWordCount(count: Int) {
        val displayText = "Word Count : $count"
        _viewState.value?.wordCount?.postValue(displayText)
    }

    fun getWordCount(str: String): Int {
        val words = str.trim().split("\\s+".toRegex())
        Log.d("apple word size", words.size.toString())
        return words.size
    }

    fun getCurrentWord(): String {
        return _viewState.value?.words?.value ?: ""
    }

    fun saveWordCountToStack(wordCountPair: Pair<String, Int>) {
        _viewState.value?.wordStack?.add(wordCountPair)
    }

    fun enableUndo() {
        _viewState.value?.isUndoBtnOn = true
    }

    fun disableUndo() {
        _viewState.value?.isUndoBtnOn = false
    }

    private fun isUndoEnabled(): Boolean {
        return _viewState.value?.isUndoBtnOn ?: false
    }

    private fun getPreviousWordCountPair(): Pair<String, Int> {
        if(_viewState.value?.wordStack?.isNotEmpty() == true) {
            _viewState.value?.wordStack?.pop()
        }
        return if(_viewState.value?.wordStack?.isNotEmpty() == true) {
            _viewState.value?.wordStack?.pop() ?: Pair("", 0)
        } else {
            Pair("", 0)
        }
    }

    private fun updateEditText(word: String) {
        _viewState.value?.words?.postValue(word)
    }

    override fun onViewClick(id: Int, data: Any) {
        when(id) {
            R.id.btn_undo -> {
                if(isUndoEnabled()) {
                    val wordCountPair = getPreviousWordCountPair()
                    val word = wordCountPair.first
                    val wordCont = wordCountPair.second
                    updateEditText(word)
                    updateWordCount(wordCont)

                }
                _viewEvent.undoEvent.postValue(Event(true))
            }
        }
    }

}