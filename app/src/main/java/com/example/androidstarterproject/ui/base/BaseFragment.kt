package com.example.androidstarterproject.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.view.View
import com.example.androidstarterproject.utils.RecyclerViewClickListener
import com.example.androidstarterproject.di.component.ActivityComponent

/**
 * Created by rtukpe on 13/03/2018.
 */

abstract class BaseFragment : Fragment(), MvpView, RecyclerViewClickListener {

    lateinit var baseActivity: BaseActivity private set
    override val isNetworkConnected: Boolean
        get() = baseActivity.isNetworkConnected

    val activityComponent: ActivityComponent
        get() = baseActivity.activityComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setUp(view)
    }

    override fun showLoading() {
        baseActivity.showLoading()
    }

    override fun hideLoading() {
        baseActivity.hideLoading()
    }

    override fun onError(message: String) {
        baseActivity.onError(message)
    }

    override fun onError(@StringRes resId: Int) {
        baseActivity.onError(resId)
    }

    override fun showMessage(message: String) {
        baseActivity.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        baseActivity.showMessage(resId)
    }

    override fun show(message: String, useToast: Boolean) {
        baseActivity.show(message, useToast)
    }

    override fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    override fun showKeyboard() {
        baseActivity.showKeyboard()
    }

    abstract fun bindViews(view: View)

    protected abstract fun setUp(view: View)
}