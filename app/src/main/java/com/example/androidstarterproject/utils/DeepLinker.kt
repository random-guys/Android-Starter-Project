package com.example.androidstarterproject.utils

import android.content.UriMatcher
import android.net.Uri
import android.os.Bundle

class DeepLinker {

    private val mUriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {

        // Add all links to the matcher
        for (link in Link.values()) {
            mUriMatcher.addURI("", link.path, link.ordinal)
        }
    }

    /**
     * Builds a bundle from the given URI
     */
    fun buildBundle(uri: Uri): Bundle {
        var code = mUriMatcher.match(uri)

        // Default to home
        var link = Link.HOME

        if (code == UriMatcher.NO_MATCH) {
            // Revert code to match default link
            code = link.ordinal
        } else {
            // Update default link with the matched one
            link = Link.values()[code]
        }

        val bundle = Bundle()
        //bundle.putInt(KEY_LINK, code);

        when (link) {
            Link.HOME -> {
            }
            Link.SEND_MONEY -> {
            }
            Link.PAY -> {
            }
        }
        return bundle
    }
}