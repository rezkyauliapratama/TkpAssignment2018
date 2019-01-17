package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.adapter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.ObservableView


interface ArticleAdapterView: ObservableView<ArticleAdapterView.Listener> {

    interface Listener {
        fun onItemclicked(url : String,title: String)
    }

    fun bindList(articleSchema: ArticleSchema)

}