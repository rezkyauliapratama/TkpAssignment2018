package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common

import android.net.Uri
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.BuildConfig
import org.jetbrains.anko.AnkoLogger

//class yang berisi url untuk API
object ObjectUrl : AnkoLogger {

    val total_row = 20

    fun searchGithub(s : String, page : Int): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("search")
                .appendPath("users")
                .appendQueryParameter("q",s)
                .appendQueryParameter("page", page.toString())
                .appendQueryParameter("per_page", total_row.toString())
                .build()
                .toString()
    }


    fun getSources(): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("v2")
            .appendPath("sources")
            .appendQueryParameter("apiKey",BuildConfig.API_KEY)
            .build()
            .toString()
    }


    fun getArticles(page : Int,source : String, q : String): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon().run{
            appendPath("v2")
            appendPath("everything")
            appendQueryParameter("domains",source)
            if (q.isNotEmpty()){
                appendQueryParameter("q",q)
            }
            appendQueryParameter("apiKey",BuildConfig.API_KEY)
            appendQueryParameter("sortBy", "publishedAt")
            appendQueryParameter("page", page.toString())
            appendQueryParameter("pageSize", total_row.toString())
            .build()
            .toString()
        }

    }


}