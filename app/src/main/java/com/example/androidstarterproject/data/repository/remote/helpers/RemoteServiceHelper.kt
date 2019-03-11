package com.example.androidstarterproject.data.repository.remote.helpers

import com.example.androidstarterproject.data.repository.remote.api.RemoteServiceApi
import com.example.androidstarterproject.data.repository.remote.helpers.base.BaseHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rtukpe on 22/03/2018.
 */

class RemoteServiceHelper @Inject
internal constructor() : BaseHelper(), RemoteServiceApi {

}
