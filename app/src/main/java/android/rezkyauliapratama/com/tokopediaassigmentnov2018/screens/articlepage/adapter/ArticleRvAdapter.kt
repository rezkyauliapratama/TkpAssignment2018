package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.adapter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.Recyclerview.BaseAdapter
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ArticleRvAdapter(private val viewMvcFactory: ViewMvcFactory, private val listener: ArticleRvAdapter.Listener):
    BaseAdapter<ArticleRvAdapter.ViewHolder>(),ArticleAdapterView.Listener{

    interface Listener {
        fun onItemclicked(url: String, title: String)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mViewMvc.bindList(mItems[position])
    }

    fun bindItems(items: List<ArticleSchema>) {
        if (!items.isEmpty()){
            mItems.addAll(items)
        }
        notifyDataSetChanged()
    }


    private val mItems: MutableList<ArticleSchema> = mutableListOf()
    private val mListener = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getArticleAdapterView(parent)
        viewMvc.registerListener(this)
        return ViewHolder(viewMvc)
    }

    override fun getItemCount() = mItems.size

    fun removeData() {
        mItems.clear()
        notifyDataSetChanged()
    }

    fun pageLoaded(){
        setLoaded()
    }

    override fun onItemclicked(url: String, title: String) {
        mListener.onItemclicked(url,title)
    }

    class ViewHolder(val mViewMvc: ArticleAdapterView) : RecyclerView.ViewHolder(mViewMvc.dataBinding.root)

}