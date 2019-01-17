package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.BR
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.R
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ListItemSourceBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.BaseObservableView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

class SourceAdapterViewImpl (inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseObservableView<SourceAdapterView.Listener>(), SourceAdapterView {

    var binding: ListItemSourceBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_source,parent,false)

    init {
        dataBinding = binding
    }

    override fun bindList(sourceSchema: SourceSchema) {
        binding.setVariable(BR.source,sourceSchema)
        binding.executePendingBindings()

        binding.root.setOnClickListener {
            for (listener in listeners){
                listener.onItemclicked(sourceSchema.url.run { if(startsWith("www.")) substring(4) else this },sourceSchema.name)
            }
        }
    }

}
