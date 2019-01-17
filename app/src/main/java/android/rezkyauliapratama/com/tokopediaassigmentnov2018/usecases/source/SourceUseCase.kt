package android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.common.UseCase
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
interface SourceUseCase :
    UseCase<SourceUseCase.Notify> {

    sealed class Notify {
        data class OnSuccess(val sources:  MutableList<SourceSchema>) : Notify()
        data class OnError(val message: String) : Notify()
    }

    fun execute()
}
