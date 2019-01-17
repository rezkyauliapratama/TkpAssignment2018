package android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter

import android.content.Context
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.fragmentframehelper.FragmentFrameHelper
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.fragmentframehelper.FragmentFrameWrapper
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.viewmodel.ViewModelFactoryImpl
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class MvcWrapperModule{

    @Provides
    fun getViewMvcFactory(@ActivityContext context: Context): ViewMvcFactory {
        return ViewMvcFactory(LayoutInflater.from(context))
    }

    @Provides
    fun getViewModel(viewModelFactory: ViewModelFactory): ViewModelFactoryImpl {
        return ViewModelFactoryImpl(viewModelFactory)
    }


    @Provides
    fun getScreensNavigator(activity: FragmentActivity): ScreensNavigator {
        return ScreensNavigator(getFragmentFrameHelper(activity), activity)
    }

    @Provides
    fun getFragmentManager(activity: FragmentActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun getFragmentFrameHelper(activity: FragmentActivity): FragmentFrameHelper? {
        return FragmentFrameHelper(activity, getFragmentFrameWrapper(activity), getFragmentManager(activity))
    }

    @Provides
    fun getFragmentFrameWrapper(activity: FragmentActivity): FragmentFrameWrapper? {
        return if (activity is FragmentFrameWrapper)
            activity
        else
            null
    }

}