package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller.MainViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class ArticleViewModel : ViewModel() {

    sealed class State {
        data class ArticleLoaded(val sources: MutableList<ArticleSchema>) : State()
        object clearArticles : State()
        object ShowLoading : State()
        object HideLoading : State()
        data class ShowError(val message: String) : State()
        object HideError: State()
        object pageLoaded : State()

    }

    abstract fun getState(): LiveData<State>

    abstract fun searchArticles(query: String, source: String)
    abstract fun fetch()
}
