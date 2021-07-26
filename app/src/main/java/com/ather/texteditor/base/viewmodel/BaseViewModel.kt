package com.ather.texteditor.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ather.texteditor.base.ViewOnClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), ViewOnClickListener {
    override fun onViewClick(id: Int, data: Any) {}
}
