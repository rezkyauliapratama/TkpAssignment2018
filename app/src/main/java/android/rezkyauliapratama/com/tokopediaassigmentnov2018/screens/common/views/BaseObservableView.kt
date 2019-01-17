package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.view.ArticleView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.view.MainView
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.adapter.SourceAdapterView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.util.*

abstract class BaseObservableView<ListenerType> : BaseView(), ObservableView<ListenerType>, AnkoLogger {

    private val mListeners = HashSet<ListenerType>()

    protected val listeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(mListeners)


    override fun registerListener(listener: ListenerType) {
        if (listener is SourceAdapterView.Listener){
            error { "listener sourceAdapter is add" }
        }
        if (listener is MainView.Listener){
            error { "listener MainView is add" }

        }
        if (listener is ArticleView.Listener){
            error { "listener ArticleView is add" }

        }
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        if (listener is SourceAdapterView.Listener){
            error { "listener sourceAdapter is remove" }
        }
        if (listener is MainView.Listener){
            error { "listener MainView is remove" }

        }
        if (listener is ArticleView.Listener){
            error { "listener ArticleView is remove" }

        }
        mListeners.remove(listener)
    }
}
