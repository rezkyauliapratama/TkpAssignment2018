package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.view

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.R
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ActivityArticleBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.adapter.ArticleRvAdapter
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleActivity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.Recyclerview.BaseAdapter
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.BaseObservableView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

class ArticleViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseObservableView<ArticleView.Listener>(),
    ArticleView, ArticleRvAdapter.Listener {

    var binding: ActivityArticleBinding = DataBindingUtil.inflate(inflater, R.layout.activity_article, parent, false)
    private var adapter: ArticleRvAdapter

    init {


        dataBinding = binding

        adapter = ArticleRvAdapter(viewMvcFactory,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(getContext())
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.isEnabled = false


        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                for(listener in listeners){
                    listener.onSearch(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        adapter.setOnLoadMoreListener(object: BaseAdapter.OnLoadMoreListener{
            override fun onLoadMore() {

                for (listener in listeners){
                    listener.onFetchPage()
                }
            }
        })

    }

    override fun onItemclicked(url: String, title: String) {
        for(listener in listeners){
            listener.onItemClicked(url,title)
        }
    }

    override fun initData() {

    }

    override fun setSupportbar(title: String) {
        (getContext() as ArticleActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (getContext() as ArticleActivity).supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                // ProjectsActivity is my 'home' activity
                for(listener in listeners){
                    listener.onBackClicked()
                }
                return true
            }
        }
        return false
    }


    override fun pageLoaded() {
        adapter.setLoaded()
    }

    override fun bindItems(response: MutableList<ArticleSchema>) {
        adapter.bindItems(response)
    }

    override fun clearItems() {
        adapter.removeData()
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
}

