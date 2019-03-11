package com.example.androidstarterproject.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidstarterproject.R
import com.example.androidstarterproject.di.component.ActivityComponent
import com.example.androidstarterproject.di.component.DaggerActivityComponent
import com.example.androidstarterproject.di.module.ActivityModule
import com.example.androidstarterproject.root.MvpApp
import com.example.androidstarterproject.utils.CommonUtils
import com.example.androidstarterproject.utils.MyProgressDialog
import com.example.androidstarterproject.utils.NetworkUtils
import com.example.androidstarterproject.utils.RecyclerViewClickListener
import com.google.android.material.snackbar.Snackbar

/**
 * Created by rtukpe on 13/03/2018.
 */

abstract class BaseActivity : AppCompatActivity(), MvpView, RecyclerViewClickListener {

    var onBackClickedListener: OnBackClicked? = null
    private lateinit var mProgressDialog: MyProgressDialog
    lateinit var activityComponent: ActivityComponent
        private set

    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as MvpApp).component).build()

        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        bindViews()
        setUp()
    }

    override fun onBackPressed() {
        if (onBackClickedListener != null) {
            onBackClickedListener?.onBackClicked()
        }
        super.onBackPressed()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun showLoading() {
        mProgressDialog.show()
    }

    override fun hideLoading() {
        if (mProgressDialog.isShowing && !this.isFinishing) {
            mProgressDialog.dismiss()
        }
    }

    override fun show(message: String, useToast: Boolean) {
        if (useToast)
            showMessage(message)
        else
            showSnackBar(message)
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view

        val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    override fun onError(message: String) {
        showSnackBar(message)
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    fun onFragmentAttached() {

    }

    fun onFragmentDetached(tag: String) {

    }

    override fun hideKeyboard() {
        val view: View? = this.currentFocus
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun showKeyboard() {
        val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    abstract fun bindViews()

    protected abstract fun setUp()

    open fun logout() {
        hideLoading()
        hideKeyboard()
    }

    interface OnBackClicked {
        fun onBackClicked()
    }
}
