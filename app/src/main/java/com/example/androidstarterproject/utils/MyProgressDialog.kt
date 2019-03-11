package com.example.androidstarterproject.utils

import android.app.Dialog
import android.content.Context
import com.example.androidstarterproject.R

class MyProgressDialog(context: Context) : Dialog(context, R.style.Dialog) {
    companion object {

        fun show(context: Context): MyProgressDialog {
            val dialog = MyProgressDialog(context)
            dialog.setCancelable(false)
            return dialog
        }
    }
}