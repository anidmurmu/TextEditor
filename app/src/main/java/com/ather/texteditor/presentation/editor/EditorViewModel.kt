package com.ather.texteditor.presentation.editor

import android.app.Application
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

    val viewEvent = EditorEvent()

    init {
        _viewState.value = EditorViewState()
    }

    override fun onViewClick(id: Int, data: Any) {
        when(id) {
            R.id.btn_undo -> {
                viewEvent.undoEvent.postValue(Event(true))
            }
        }
    }

}