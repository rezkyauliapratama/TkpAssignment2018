package android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.application

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.BaseApplication
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.TimeUtility
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.DataManager
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent{
    fun inject(baseApplication: BaseApplication)
    fun getDataManager(): DataManager
    fun getTimeUtils(): TimeUtility

/*
    fun inject(baseApplication: BaseApplication)*/

}