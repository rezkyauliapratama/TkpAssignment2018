package android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.common.UseCase


interface ArticlesUseCase :
    UseCase<ArticlesUseCase.Notify> {

    sealed class Notify {
        data class OnSearchSuccess(val sources:  MutableList<ArticleSchema>) : Notify()
        data class OnFetchSuccess(val sources:  MutableList<ArticleSchema>) : Notify()
        data class OnError(val message: String) : Notify()
    }

    fun search(query: String,source: String)
    fun fetchdata()
    fun initialize()
}
