package com.example.androidstarterproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidstarterproject.R
import com.example.androidstarterproject.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun bindViews() {

    }

    override fun setUp() {

    }

    override fun recyclerViewItemClicked(v: View, position: Int) {

    }
}
