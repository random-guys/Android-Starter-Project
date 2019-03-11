package com.example.androidstarterproject.ui.base

import com.example.androidstarterproject.ui.base.MvpView

/**
 * Created by janisharali on 24/05/17.
 */

interface DialogMvpView : MvpView {

    fun dismissDialog(tag: String)
}
