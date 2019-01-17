package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.view

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.R
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter.SourcesRvAdapter
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.BaseObservableView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager


class MainViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseObservableView<MainView.Listener>(),
    MainView, SourcesRvAdapter.Listener {


    var binding: ActivityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, parent, false)
    private var adapter: SourcesRvAdapter

    init {
        dataBinding = binding

        adapter = SourcesRvAdapter(viewMvcFactory,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(getContext())
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.isEnabled = false

/*
        adapter.setOnLoadMoreListener(object: BaseAdapter.OnLoadMoreListener{
            override fun onLoadMore() {
                for (listener in listeners){
                    listener.onFetchPage()
                }

            }
        })*/
    }

    override fun bindItems(sources: MutableList<SourceSchema>) {
        adapter.bindItems(sources)
    }

    override fun showProgressIndication() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgressIndication() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun showStatusIndication(message: String) {
        binding.tvStatus.text = message
        binding.layoutStatus.visibility = View.VISIBLE
    }

    override fun hideStatusIndication() {
        binding.tvStatus.text = ""
        binding.layoutStatus.visibility = View.GONE
    }

    override fun onItemclicked(url: String, title: String) {
        for (listener in listeners){
            listener.onItemClicked(url, title)
        }
    }
}

