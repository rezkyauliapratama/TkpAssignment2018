package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views


interface ObservableView<ListenerType> : ViewInterface {

    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}
