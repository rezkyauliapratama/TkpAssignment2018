package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.R
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ActivityDetailBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.BaseObservableView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil


class DetailViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseObservableView<DetailView.Listener>(), DetailView{



    var binding: ActivityDetailBinding = DataBindingUtil.inflate(inflater, R.layout.activity_detail, parent, false)

    init {


        dataBinding = binding

        val webSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                loadedFinish()
            }
        }

    }


    override fun setSupportbar(title: String) {
        (getContext() as DetailActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (getContext() as DetailActivity).supportActionBar?.title = title
    }

    override fun loadUrl(url: String) {
        binding.webView.loadUrl(url)

    }

    override fun loadedFinish() {
        binding.progressBar.visibility = View.GONE
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

}

