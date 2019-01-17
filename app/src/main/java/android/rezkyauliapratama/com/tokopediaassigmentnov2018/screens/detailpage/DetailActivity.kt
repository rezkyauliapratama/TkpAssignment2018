package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage

import android.os.Bundle
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ActivityDetailBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.controller.BaseActivity
import android.view.MenuItem


class DetailActivity : BaseActivity<DetailViewModel, DetailView, ActivityDetailBinding>(),DetailView.Listener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getStringArrayExtra("data")

        mViewMvc.setSupportbar(data[1])
        mViewMvc.loadUrl(data[0])
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return mViewMvc.onOptionsItemSelected(item)

    }


    override fun inject() {
        initControllerComponent()?.inject(this)
    }

    override fun initView() {
        mViewMvc = viewMvcFactory.getDetailView(null)

    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityDetailBinding

    }

    override fun initViewModel(): DetailViewModel {
        return viewModelFactoryImpl.getDetailViewModel(this)

    }

    override fun onBackClicked() {
        finish()
    }

    override fun registerListener() {
        mViewMvc.registerListener(this)
    }

    override fun unregisterListener() {
        mViewMvc.registerListener(this)

    }
}