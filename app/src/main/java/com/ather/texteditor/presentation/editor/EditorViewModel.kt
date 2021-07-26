package com.ather.texteditor.presentation.editor

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ather.texteditor.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EditorViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    private val _viewState = MutableLiveData<EditorViewState>()
    val viewState: LiveData<EditorViewState> = _viewState

    init {
        _viewState.value = EditorViewState()
    }


}