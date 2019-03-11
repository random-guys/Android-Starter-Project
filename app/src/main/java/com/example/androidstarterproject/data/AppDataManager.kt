package com.example.androidstarterproject.data

import android.content.Context
import com.example.androidstarterproject.data.repository.local.helper.LocalDataHelper
import com.example.androidstarterproject.data.repository.mock.MockHelper
import com.example.androidstarterproject.data.repository.remote.helpers.RemoteServiceHelper
import com.example.androidstarterproject.di.annotations.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rtukpe on 14/03/2018.
 */

@Singleton
class AppDataManager
@Inject
internal constructor(@param:ApplicationContext val context: Context,
                     private val mLocalDataHelper: LocalDataHelper,
                     private val mRemoteServiceHelper: RemoteServiceHelper,
                     private val mMockHelper: MockHelper
) : DataManager {


}