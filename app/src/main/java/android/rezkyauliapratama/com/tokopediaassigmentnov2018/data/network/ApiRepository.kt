package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.api.ArticleApi
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.api.SourceApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Rezky Aulia Pratama on 11/9/18.
 */


@Singleton
class ApiRepository @Inject constructor(){


    @Inject
    lateinit var sourceApi: SourceApi

    @Inject
    lateinit var articleApi: ArticleApi

    //add another management file in here if it've more than 1 api

}