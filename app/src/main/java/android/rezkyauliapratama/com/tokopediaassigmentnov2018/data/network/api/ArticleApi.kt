package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.api

import android.os.Parcelable
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.ConnectivityUtil
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common.BaseResponse
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common.ObjectUrl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import com.google.gson.Gson
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.ObservableSource
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class ArticleApi @Inject constructor(private val networkClient: NetworkClient, private val connectivityUtil: ConnectivityUtil) : AnkoLogger{
    val TAG : String  = ArticleApi::class.java.simpleName


    fun getAllArticles(page:Int, source: String, q : String) : ObservableSource<ArticleResponse>{
        return ObservableSource {
            emitter ->
            try {
                retrieveAllArticles(page,source,q)
                        .also { Gson().toJson(it) }
                        .apply { emitter.onNext(this) }

            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }


    fun fetchArticles(page: Int, source: String, q: String): Single<ArticleResponse> {

        return Single.create<ArticleResponse>{ emitter ->
            try {
                retrieveAllArticles(page,source,q)
                    .also { Gson().toJson(it) }
                    .apply { emitter.onSuccess(this) }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }


    //function untuk mengambil data article dari api
    private fun retrieveAllArticles(page:Int, source : String, q : String) : ArticleResponse
    {
        val searchTag = "$TAG-query "


        if (connectivityUtil.isNetworkAvailable()){

            try
            {
                error{
                    "with search : ${ObjectUrl.getArticles(page,source,q)}"
                }
                return with(networkClient){
                    cancelByTag(searchTag)
                    withUrl(ObjectUrl.getArticles(page,source,q))
                        .initAs(ArticleResponse::class.java)
                        .setTag(searchTag)
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
    data class ArticleResponse(
        val status : String,
        val totalResults : String,
        val articles : List<ArticleSchema>,
        val code: String,
        val message: String
    ) : Parcelable

}