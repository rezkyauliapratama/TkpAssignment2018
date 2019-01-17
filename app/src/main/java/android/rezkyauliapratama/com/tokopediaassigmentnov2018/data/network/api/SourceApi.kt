package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.api

import android.os.Parcelable
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.ConnectivityUtil
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common.BaseResponse
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common.ObjectUrl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class SourceApi @Inject constructor(private val networkClient: NetworkClient, private val connectivityUtil: ConnectivityUtil) : AnkoLogger {

    val TAG : String  = SourceApi::class.java.simpleName

    fun getAll(): Single<SourcesResponse> {
        return Single.create<SourcesResponse> { emitter ->
            try {
                retrieveAllSources()
                        .apply { emitter.onSuccess(this) }

            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    private fun retrieveAllSources() : SourcesResponse
    {

        if (connectivityUtil.isNetworkAvailable()){

            try
            {
                error { "url : ${ObjectUrl.getSources()}" }
                return with(networkClient){
                    withUrl(ObjectUrl.getSources())
                        .initAs(SourcesResponse::class.java)
                        .setTag(TAG)
                        .syncFuture
                }
            } catch (e: Exception) {
                throw e
            }

        }else{
            throw Exception("No Network")
        }


    }


    @Parcelize
    data class SourcesResponse (
        val status : String,
        var sources : List<SourceSchema>,
        val code: String,
        val message: String
    ) : Parcelable
}