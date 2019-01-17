package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


abstract class MainViewModel : ViewModel() {

    sealed class State {
        data class SourcesLoaded(val sources: MutableList<SourceSchema>) : State()
        object ShowLoading : State()
        object HideLoading : State()
        data class ShowError(val message: String) : State()
        object HideError: State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchSources()
}
