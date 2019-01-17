package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.view.ArticleView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.view.ArticleViewImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.adapter.ArticleAdapterView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.adapter.ArticleAdapterViewImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailViewImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.view.MainView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.view.MainViewImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter.SourceAdapterView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter.SourceAdapterViewImpl
import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {
    fun getSourceAdapterView(parent: ViewGroup): SourceAdapterView {
        return SourceAdapterViewImpl(mLayoutInflater,parent,this)
    }

    fun getMainView(parent: ViewGroup?): MainView {
        return MainViewImpl(
            mLayoutInflater,
            parent,
            this
        )
    }

    fun getArticleAdapterView(parent: ViewGroup): ArticleAdapterView {
        return ArticleAdapterViewImpl(mLayoutInflater,parent,this)

    }

    fun getArticleView(parent: ViewGroup?): ArticleView {
        return ArticleViewImpl(
            mLayoutInflater,
            parent,
            this
        )

    }

    fun getDetailView(parent: ViewGroup?): DetailView {
        return DetailViewImpl(mLayoutInflater, parent, this)

    }
}
