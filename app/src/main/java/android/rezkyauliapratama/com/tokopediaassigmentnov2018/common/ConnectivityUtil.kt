package android.rezkyauliapratama.com.tokopediaassigmentnov2018.common

import android.content.Context
import android.net.ConnectivityManager



class ConnectivityUtil constructor (val context: Context){

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

}