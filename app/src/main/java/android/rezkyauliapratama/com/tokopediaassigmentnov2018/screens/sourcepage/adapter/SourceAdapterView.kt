package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.ObservableView


interface SourceAdapterView : ObservableView<SourceAdapterView.Listener> {

    interface Listener {
        fun onItemclicked(url : String,title: String)
    }


    fun bindList(sourceSchema: SourceSchema)

}