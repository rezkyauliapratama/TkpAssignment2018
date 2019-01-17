package android.rezkyauliapratama.com.tokopediaassigmentnov2018

import android.app.Application
import android.beautyview.utility.BeautyView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.application.ApplicationComponent
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.application.ApplicationModule
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.application.DaggerApplicationComponent
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.application.NetworkModule
import com.squareup.leakcanary.LeakCanary


class BaseApplication : Application(){

    companion object {
        lateinit var component : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
        component.inject(this)

        LeakCanary.install(this)
        val fontFolder = "fonts/Nunito/Nunito-"

        BeautyView[fontFolder + "Regular.ttf", fontFolder + "Bold.ttf", fontFolder + "Italic.ttf"] = fontFolder + "BoldItalic.ttf"

        BeautyView.setFontScale(1f)
    }

    private fun initDagger(app: BaseApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(app))
                    .networkModule(NetworkModule())
                    .build()



}