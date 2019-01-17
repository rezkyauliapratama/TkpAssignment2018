package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.view

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.ObservableView


interface  MainView : ObservableView<MainView.Listener> {

    interface Listener {
        fun onFetchPage()
        fun onItemClicked(url: String, name: String)
    }



    fun bindItems(sources: MutableList<SourceSchema>)

    fun showProgressIndication()

    fun hideProgressIndication()

    fun showStatusIndication(message: String)

    fun hideStatusIndication()

}