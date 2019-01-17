package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller

import android.os.Bundle
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.observe
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ActivityArticleBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleViewModel.State
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.view.ArticleView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.controller.BaseActivity
import android.view.MenuItem
import org.jetbrains.anko.error

class ArticleActivity : BaseActivity<ArticleViewModel, ArticleView, ActivityArticleBinding>(),
    ArticleView.Listener {

    override fun registerListener() {
        mViewMvc.registerListener(this)
    }

    override fun unregisterListener() {
        mViewMvc.unregisterListener(this)

    }

    override fun initViewModel(): ArticleViewModel {
        error { "initviewmodel" }
        return viewModelFactoryImpl.getArticleViewModel(this)
    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityArticleBinding
    }

    override fun inject() {
        initControllerComponent()?.inject(this)
    }

    override fun initView() {
        mViewMvc = viewMvcFactory.getArticleView(null)
    }


    private var title = ""
    private var source = ""
    lateinit var data: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getStringArrayExtra("data")
        this.title = data[1]
        this.source = data[0]
        mViewMvc.setSupportbar(title)
        setUpViewModelStateObservers()
        mViewModel.searchArticles("",source)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return mViewMvc.onOptionsItemSelected(item)
    }


    override fun onBackClicked() {
        finish()
    }

    private fun setUpViewModelStateObservers() {
        observe(mViewModel.getState()) { onStateChanged(it) }

    }

    private fun onStateChanged(it: State) = when (it) {
        is State.ArticleLoaded -> mViewMvc.bindItems(it.sources)
        is State.ShowLoading -> mViewMvc.showProgressIndication()
        is State.ShowError -> mViewMvc.showStatusIndication(it.message)
        is State.HideLoading -> mViewMvc.hideProgressIndication()
        is State.clearArticles -> mViewMvc.clearItems()
        is State.HideError -> mViewMvc.hideStatusIndication()
        is State.pageLoaded -> mViewMvc.pageLoaded()
    }

    override fun onItemClicked(url: String, title: String) {
        screensNavigator.toDetailActivity(url,title)
    }


    override fun onFetchPage() {
        mViewModel.fetch()
    }

    override fun onSearch(s: String) {
        mViewModel.searchArticles(s,source)
    }

}
