package android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.DataManager
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.common.BaseUseCase
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.api.SourceApi
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.SourceSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source.SourceUseCase.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.io.IOException
import java.util.regex.Pattern
import javax.inject.Inject


class SourceUseCaseImpl @Inject constructor(private val dataManager: DataManager) : BaseUseCase<Notify>(), AnkoLogger,
    SourceUseCase  {

    val messageError = "Ups sorry, our server is too busy. Please try again"

    companion object {
        val HTTP_RESPONSE_SUCCESS = "ok"
    }

    override fun execute() {
        dataManager.api
            .sourceApi
            .getAll()
            .map { response ->
                for (source : SourceSchema in response.sources){
                    val pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?")
                    val matcher = pattern.matcher(source.url)

                    matcher.find()

                    val domain = matcher.group(2)
                    source.url = domain
                }

                response
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError).track()
    }


    private fun onError(throwable: Throwable){
        error { "throwable : ${Gson().toJson(throwable)}" }

        if (throwable is IOException){
            liveData.value = Notify.OnError(messageError)
        }else{
            liveData.value = Notify.OnError(throwable.localizedMessage)
        }

    }
    private fun onSuccess(response : SourceApi.SourcesResponse){
        if (response.status == HTTP_RESPONSE_SUCCESS){
            if (response.sources.isNotEmpty()){
                liveData.value = Notify.OnSuccess(response.sources as MutableList<SourceSchema>)
            }else{
                liveData.value = Notify.OnError("Empty result")
            }
        }else{
            liveData.value = Notify.OnError(messageError)
        }

    }


}
