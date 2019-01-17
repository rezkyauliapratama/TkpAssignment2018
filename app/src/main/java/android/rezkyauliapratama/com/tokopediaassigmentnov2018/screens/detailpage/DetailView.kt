package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.ObservableView
import android.view.MenuItem


interface  DetailView : ObservableView<DetailView.Listener> {

    interface Listener {
        fun onBackClicked()
    }

    fun loadUrl(url : String)

    fun loadedFinish()
    fun setSupportbar(title: String)
    fun onOptionsItemSelected(item: MenuItem?): Boolean

}