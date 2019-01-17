package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.viewmodel

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleViewModel
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleViewModelImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailActivity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailViewModel
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller.MainViewModel
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller.MainViewModelImpl
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class ViewModelFactoryImpl @Inject constructor(val viewModelFactory: ViewModelFactory): AnkoLogger{

    fun getMainViewModel(activity: FragmentActivity): MainViewModel {
        return ViewModelProviders.of(activity, viewModelFactory).get(MainViewModelImpl::class.java)
    }

    fun getArticleViewModel(activity: FragmentActivity): ArticleViewModel{
        return ViewModelProviders.of(activity, viewModelFactory).get(ArticleViewModelImpl::class.java)
    }

    fun getDetailViewModel(activity: FragmentActivity): DetailViewModel {
        return ViewModelProviders.of(activity, viewModelFactory).get(DetailViewModel::class.java)
    }

}
