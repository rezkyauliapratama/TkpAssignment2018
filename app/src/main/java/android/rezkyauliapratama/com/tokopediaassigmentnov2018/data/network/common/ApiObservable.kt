package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common

import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Rezky Aulia Pratama on 1/10/18.
 */

abstract class ApiObservable<LISTENER_CLASS> : AnkoLogger {


    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    // thread-safe set of listeners
    private val mListeners = Collections.newSetFromMap(
            ConcurrentHashMap<LISTENER_CLASS, Boolean>(1))

    protected val listeners: Set<LISTENER_CLASS>
        get() = Collections.unmodifiableSet(mListeners)


    fun registerListener(listener: LISTENER_CLASS) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_CLASS) {
        mListeners.remove(listener)
        compositeDisposable.dispose()
    }


    companion object {
        val HTTP_RESPONSE_SUCCESS = "ok"
    }

}
