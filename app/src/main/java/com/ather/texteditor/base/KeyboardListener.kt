package com.ather.texteditor.base

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver

fun View.setKeypadListener(keyPadListener: KeyPadListener) {
    viewTreeObserver.addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
        val r = Rect()
        getWindowVisibleDisplayFrame(r)
        val screenHeight: Int = rootView.height
        val keypadHeight: Int = screenHeight - r.bottom
        if (keypadHeight > screenHeight * 0.15) {
            keyPadListener.onKeypadShown()
        } else {
            keyPadListener.onKeypadHidden()
        }
    })
}

interface KeyPadListener {
    fun onKeypadShown()
    fun onKeypadHidden()
}