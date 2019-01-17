package android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.TimeUtility
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.DataManager
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.api.ArticleApi
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleViewModel
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article.ArticlesUseCase.Notify
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.common.BaseUseCase
import com.google.gson.Gson
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ArticleUseCaseImpl @Inject constructor(private val dataManager: DataManager, private var timeUtility: TimeUtility) : BaseUseCase<Notify>(), AnkoLogger,
    ArticlesUseCase {

    var subject : PublishSubject<String>
    private var page : Int = 1

    private var queryHolder: String = ""
    private var sourceHolder: String = ""

    val messageError = "Ups sorry, our server is too busy. Please try again"

    companion object {
        val HTTP_RESPONSE_SUCCESS = "ok"
    }

    init {
        error { "init" }
        subject = PublishSubject.create<String>()
        initialize()
    }

    override fun initialize(){
            subject.
                debounce(300, TimeUnit.MILLISECONDS)
                /*.filter(Predicate { it: String ->
                    return@Predicate it.isNotEmpty()
                })*/
                .distinctUntilChanged()
                .switchMap(Function<String, ObservableSource<ArticleApi.ArticleResponse>> { it ->
                    page = 1
                    error { "it $it | page $page" }
                    return@Function dataManager.api
                        .articleApi
                        .getAllArticles(page,sourceHolder,it)
                })
                .map { it ->
                    for (article: ArticleSchema in it.articles){
                        timeUtility.run { convertStringToDate(article.publishedAt).also { article.publishedAt = getFriendlyDate(it) } }
                    }
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onSuccess,::onError).track()
    }

    override fun search(query: String, source: String) {
        this.queryHolder = query
        this.sourceHolder = source

        subject.onNext(query)



    }

    override fun fetchdata() {
        page++
            dataManager.api
                .articleApi
                .fetchArticles(page,sourceHolder,queryHolder)
                .map { it ->
                    for (article: ArticleSchema in it.articles){
                        timeUtility.run { convertStringToDate(article.publishedAt).also { article.publishedAt = getFriendlyDate(it) } }
                    }
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onFetchSuccess,::onError).track()

    }



    private fun onError(throwable: Throwable){
        error { "throwable : ${Gson().toJson(throwable)}" }

        if (throwable is IOException){
            liveData.value = Notify.OnError(messageError)
        }else{
            liveData.value = Notify.OnError(throwable.localizedMessage)
        }

    }
    private fun onSuccess(response : ArticleApi.ArticleResponse){
        if (response.status == HTTP_RESPONSE_SUCCESS){
            if (response.articles.isNotEmpty()){
                liveData.value = Notify.OnSearchSuccess(response.articles as MutableList<ArticleSchema>)
            }else{
                liveData.value = Notify.OnError("Empty result")
            }
        }else{
            liveData.value = Notify.OnError(messageError)
        }

    }

     private fun onFetchSuccess(response : ArticleApi.ArticleResponse){
            if (response.status == HTTP_RESPONSE_SUCCESS){
                if (response.articles.isNotEmpty()){
                    liveData.value = Notify.OnFetchSuccess(response.articles as MutableList<ArticleSchema>)
                }else{
                    liveData.value = Notify.OnError("Empty result")
                }
            }else{
                liveData.value = Notify.OnError(messageError)
            }

        }


}