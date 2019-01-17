package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.TimeUtility
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article.ArticleUseCaseImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article.ArticlesUseCase.Notify
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject


class ArticleViewModelImpl @Inject constructor(var articleUseCase: ArticleUseCaseImpl) : ArticleViewModel(),
    AnkoLogger {


    private var state: MediatorLiveData<State> = MediatorLiveData()
    private var mitems: MutableList<ArticleSchema>? = null

    init {
        state.addSource(articleUseCase.getLiveData(), ::onFetchSourcesNotify)

    }


    override fun searchArticles(query: String, source: String) {
        state.value = ArticleViewModel.State.ShowLoading
        articleUseCase.search(query,source)
    }

    override fun fetch() {
        state.value = State.ShowLoading
        articleUseCase.fetchdata()
    }

    override fun onCleared() {
        super.onCleared()
        articleUseCase.cleanUp()
    }

    override fun getState(): LiveData<State> = state


    private fun onFetchSourcesNotify(result: Notify) {
        when (result) {
            is Notify.OnSearchSuccess -> {
                mitems = result.sources
                state.value = State.clearArticles
                state.value = State.ArticleLoaded(result.sources)
                state.value = State.HideLoading
                state.value = State.HideError
            }
            is Notify.OnFetchSuccess ->{
                mitems = result.sources
                state.value = State.ArticleLoaded(result.sources)
                state.value = State.HideLoading
                state.value = State.HideError
                state.value = State.pageLoaded
            }
            is Notify.OnError -> {
                state.value = State.ShowError(result.message)
                state.value = State.HideLoading
                state.value = State.clearArticles

            }
        }
    }
}
