package com.ather.texteditor.presentation.editor

import androidx.lifecycle.MutableLiveData
import com.ather.texteditor.base.event.Event

class EditorEvent {
    val undoEvent: MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
}