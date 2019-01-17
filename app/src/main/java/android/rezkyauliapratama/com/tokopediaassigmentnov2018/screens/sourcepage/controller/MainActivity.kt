package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.observe
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.controller.BaseActivity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller.MainViewModel.State
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.view.MainView
import org.jetbrains.anko.error

class MainActivity : BaseActivity<MainViewModel, MainView, ActivityMainBinding>() , MainView.Listener{
    override fun registerListener() {
        mViewMvc.registerListener(this)
    }

    override fun unregisterListener() {
        mViewMvc.unregisterListener(this)

    }

    override fun initViewModel(): MainViewModel {
      return viewModelFactoryImpl.getMainViewModel(this)
    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityMainBinding
    }

    override fun inject() {
        initControllerComponent()?.inject(this)
    }

    override fun initView() {
        mViewMvc = viewMvcFactory.getMainView(null)
    }



    override fun onStart() {
        super.onStart()
        setUpViewModelStateObservers()
        mViewModel.fetchSources()

    }

    private fun setUpViewModelStateObservers() {
        observe(mViewModel.getState()) { onStateChanged(it) }

    }

    private fun onStateChanged(it: State) = when (it) {
        is State.SourcesLoaded -> mViewMvc.bindItems(it.sources)
        is State.ShowLoading -> mViewMvc.showProgressIndication()
        is State.ShowError -> mViewMvc.showStatusIndication(it.message)
        is State.HideLoading -> mViewMvc.hideProgressIndication()
        is State.HideError -> mViewMvc.hideStatusIndication()
    }

    override fun onFetchPage() {

    }

    override fun onItemClicked(url: String, name: String) {
        screensNavigator.toArticleActivity(url,name)
    }

}
