package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.view

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.ObservableView
import android.view.MenuItem


interface  ArticleView : ObservableView<ArticleView.Listener> {

    interface Listener {
        fun onSearch(s: String)
        fun onFetchPage()
        fun onItemClicked(url: String, title: String)
        fun onBackClicked()
    }

    fun initData()

    fun bindItems(response: MutableList<ArticleSchema>)

    fun pageLoaded()

    fun clearItems()

    fun showProgressIndication()

    fun hideProgressIndication()

    fun showStatusIndication(message: String)

    fun hideStatusIndication()

    fun setSupportbar(title: String)

    fun onOptionsItemSelected(item: MenuItem?): Boolean
}