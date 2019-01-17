package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.controller

import android.os.Bundle
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.BaseApplication
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter.ActivityModule
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter.ControllerComponent
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter.DaggerControllerComponent
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter.MvcWrapperModule
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.viewmodel.ViewModelFactoryImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.ViewInterface
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject


abstract class BaseActivity<T : ViewModel, U : ViewInterface, V : ViewDataBinding>  : AppCompatActivity(), AnkoLogger{

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewModelFactoryImpl: ViewModelFactoryImpl

    lateinit var mViewModel: T

    lateinit var mViewMvc: U

    lateinit var mDataBinding: V

    abstract fun inject()
    abstract fun initView()
    abstract fun initDataBinding()
    abstract fun registerListener()
    abstract fun unregisterListener()
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun initViewModel(): T


    private var controllerComponent: ControllerComponent ?= null

    //function untuk init activity component
    fun initControllerComponent(): ControllerComponent? {
        if (controllerComponent == null) {
            controllerComponent = DaggerControllerComponent.builder()
                .applicationComponent(BaseApplication.component)
                .activityModule(ActivityModule(this))
                .build()
        }
        return controllerComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        initView()
        initDataBinding()
        registerListener()
        setContentView(mViewMvc.dataBinding.root)
        this.mViewModel =  initViewModel()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterListener()
    }



}