package com.example.androidstarterproject.utils

interface OnChildDidClickNext {
    fun onPreviousClick(index: Int)
    fun onNextClick(index: Int, data: Any)
}