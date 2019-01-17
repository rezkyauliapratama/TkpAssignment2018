package android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.common

import androidx.lifecycle.LiveData

interface UseCase<T> {

    fun getLiveData(): LiveData<T>

    fun cleanUp()
}
