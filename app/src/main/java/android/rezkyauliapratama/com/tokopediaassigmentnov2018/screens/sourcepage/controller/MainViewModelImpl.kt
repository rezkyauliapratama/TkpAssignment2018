package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source.SourceUseCase
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source.SourceUseCaseImpl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject


class MainViewModelImpl @Inject constructor(var sourcesUseCase: SourceUseCaseImpl) : MainViewModel(), AnkoLogger {

    private var state: MediatorLiveData<State> = MediatorLiveData()
    private var mitems: MutableList<SourceSchema>? = null

    init {
        state.addSource(sourcesUseCase.getLiveData(), ::onFetchSourcesNotify)
    }


    override fun fetchSources() {
        if (mitems != null) {
            state.value =
                    State.SourcesLoaded(
                        mitems as MutableList<SourceSchema>
                    )
        }else{
            state.value =
                    State.ShowLoading
            sourcesUseCase.execute()
        }

    }

    override fun onCleared() {
        super.onCleared()
        sourcesUseCase.cleanUp()
    }

    override fun getState(): LiveData<State> = state


    private fun onFetchSourcesNotify(result: SourceUseCase.Notify) {
        when (result) {
            is SourceUseCase.Notify.OnSuccess -> {
                mitems = result.sources
                state.value =
                        State.SourcesLoaded(
                            result.sources
                        )
                state.value =
                        State.HideLoading
            }
            is SourceUseCase.Notify.OnError -> {
                state.value =
                        State.ShowError(
                            result.message
                        )
                state.value =
                        State.HideLoading
            }
        }
    }
}
