package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.Recyclerview.BaseAdapter
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class SourcesRvAdapter(private val viewMvcFactory: ViewMvcFactory,
                       listener : SourcesRvAdapter.Listener
):
    BaseAdapter<SourcesRvAdapter.ViewHolder>(),SourceAdapterView.Listener{


    interface Listener {
        fun onItemclicked(url: String, title: String)
    }

    private val mListeners = listener


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mAdapterViewMvc.bindList(mItems[position])
    }

    fun bindItems(items: List<SourceSchema>) {
        mItems.clear()
        if (!items.isEmpty()){
            mItems.addAll(items)
        }
        notifyDataSetChanged()
        setLoaded()
    }
    private val mItems: MutableList<SourceSchema> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getSourceAdapterView(parent)
        viewMvc.registerListener(this)
        return ViewHolder(viewMvc)
    }

    override fun getItemCount() = mItems.size

    override fun onItemclicked(url: String, title: String) {
            mListeners.onItemclicked(url,title)
    }

    class ViewHolder(val mAdapterViewMvc: SourceAdapterView) : RecyclerView.ViewHolder(mAdapterViewMvc.dataBinding.root)

}