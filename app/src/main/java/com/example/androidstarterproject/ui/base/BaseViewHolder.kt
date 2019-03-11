package com.example.androidstarterproject.ui.base

import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * Created by rtukpe on 14/03/2018.
 */

abstract class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    var currentPosition: Int = 0
        private set

    open fun onBind(position: Int) {
        currentPosition = adapterPosition
    }
}