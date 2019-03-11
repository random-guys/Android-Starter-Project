package com.example.androidstarterproject.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import com.example.androidstarterproject.R
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * Created by rtukpe on 13/03/2018.
 */

object CommonUtils {

    private val TAG = "CommonUtils"

    val timeStamp: String = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())

    fun showLoadingDialog(context: Context): MyProgressDialog {
        val progressDialog = MyProgressDialog.show(context)
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun isEmailValid(email: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(emailPattern)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    /**
     * 1.5% is SA's commission. Amounts converted to Kobo
     *
     * @param amount is the amount entered
     * @return the fixed charge
     */
    fun getFixedCharge(amount: Double): Int {
        var fixedCharge = (1.5 / 100 * (amount * 100)).toInt()

        if (amount >= 1000) {
            fixedCharge += 100 * 100
        }

        return fixedCharge + getPayStackCharge(amount)
    }

    private fun getPayStackCharge(amount: Double): Int {
        var retVal = (1.5 / 100 * amount * 100.0).toInt()

        if (amount > 2500) {
            retVal += 100 * 100
        }

        return if (retVal > 2000 * 100) {
            2000 * 100 //in kobo
        } else retVal
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {

        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, Charset.forName("UTF-8"))
    }
}// This utility class is not publicly instantiable
