package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.ConnectivityUtil
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.ApiRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataManager @Inject constructor(){

    @Inject
    lateinit var api: ApiRepository

    @Inject
    lateinit var connectivityUtil: ConnectivityUtil
}