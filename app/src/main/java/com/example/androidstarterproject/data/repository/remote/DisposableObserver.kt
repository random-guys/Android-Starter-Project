package com.example.androidstarterproject.data.repository.remote

import com.example.androidstarterproject.data.model.Response
import com.example.androidstarterproject.utils.AppConstants
import com.example.androidstarterproject.utils.AppUtils
import com.example.androidstarterproject.utils.Log
import com.example.androidstarterproject.utils.toSentenceCase
import com.google.gson.JsonSyntaxException
import io.reactivex.observers.DisposableObserver
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

abstract class DisposableObserver : DisposableObserver<Response>() {

    abstract fun onRequestSuccessful(response: Response, message: String)

    abstract fun onRequestFailed(code: Int, failureReason: String, response: Response?)

    abstract fun onAuthorizationError()

    override fun onComplete() {

    }

    override fun onNext(response: Response) {
        Log.d("Request Response ----> $response")
        val message = response.message?.let { it } ?: "The server didn't return a valid response"
        if (response.data == null || response.response != "00") {
            onRequestFailed(response.code, message.toSentenceCase(), response)
            return
        }
        if (response.status || response.response != null && response.response == "00") {
            onRequestSuccessful(response, message.toSentenceCase())
        }
    }

    override fun onError(e: Throwable) {
        if (e is ConnectException) {
            onRequestFailed(400, AppConstants.CONNECT_EXCEPTION, Response())
            return
        }
        if (e is UnknownHostException) {
            onRequestFailed(400, AppConstants.UNKNOWN_HOST_EXCEPTION, Response())
            return
        }
        if (e is SocketTimeoutException) {
            onRequestFailed(400, AppConstants.SOCKET_TIME_OUT_EXCEPTION, Response())
            return
        }
        if (e is HttpException) {
            try {
                var response = Response()
                try {
                    response = AppUtils.gson.fromJson<Response>(e.response().errorBody()?.string()!!, Response::class.java)
                } catch (err: JsonSyntaxException) {
                    Log.d(err.message!!)
                    onRequestFailed(403, "We are not able to process your request right now. Please try again later.", response)
                }
                val errorMessage = response.message!!
                if (e.response().code() == 401) onAuthorizationError()
                //onRequestFailed(400, errorMessage, response)
            } catch (err: JSONException) {
                Log.e("JSON Exception", err.localizedMessage)
                onRequestFailed(400, AppConstants.UNKNOWN_NETWORK_EXCEPTION, Response())
            } catch (err: IOException) {
                Log.e("Network IO Exception", err.localizedMessage)
                onRequestFailed(400, AppConstants.UNKNOWN_NETWORK_EXCEPTION, Response())
            } catch (err: SSLException) {
                Log.e("Network IO Exception", err.localizedMessage)
                onRequestFailed(400, AppConstants.SSL_EXCEPTION, Response())
            }
            return
        }
        onRequestFailed(400, AppConstants.UNKNOWN_NETWORK_EXCEPTION, Response())
    }
}