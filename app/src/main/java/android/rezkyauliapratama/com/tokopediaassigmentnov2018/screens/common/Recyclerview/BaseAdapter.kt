package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.Recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>(){

    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var isLoading: Boolean = false
    private val visibleThreshold = 5
    private var lastVisibleItem: Int = 0
    private var totalItemCount:Int = 0

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.layoutManager?.let {
                    totalItemCount = it.itemCount
                    lastVisibleItem = (it as LinearLayoutManager).findLastVisibleItemPosition()
                    if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                        onLoadMoreListener?.onLoadMore()
                        isLoading = true
                    }
                }

            }
        })
    }

    fun setLoaded() {
        isLoading = false
    }


    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
