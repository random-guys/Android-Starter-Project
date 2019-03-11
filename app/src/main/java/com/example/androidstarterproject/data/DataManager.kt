package com.example.androidstarterproject.data

import com.example.androidstarterproject.data.repository.local.interfaces.LocalDataInterface
import com.example.androidstarterproject.data.repository.mock.MockerInterface
import com.example.androidstarterproject.data.repository.remote.api.RemoteServiceApi


/**
 * Created by rtukpe on 14/03/2018.
 */

interface DataManager : RemoteServiceApi, LocalDataInterface, MockerInterface
