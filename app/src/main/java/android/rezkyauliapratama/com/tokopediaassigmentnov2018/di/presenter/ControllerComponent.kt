package android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.application.ApplicationComponent
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelModule
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleActivity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailActivity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller.MainActivity
import dagger.Component


/**
 * Created by Rezky Aulia Pratama on 15/8/18.
 */
@PerController
@Component(dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class, MvcWrapperModule::class, UseCaseModule::class,ViewModelModule::class])
interface ControllerComponent{
    fun getViewModelFactory() : ViewModelFactory


    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: ArticleActivity)
    fun inject(detailActivity: DetailActivity)
}
