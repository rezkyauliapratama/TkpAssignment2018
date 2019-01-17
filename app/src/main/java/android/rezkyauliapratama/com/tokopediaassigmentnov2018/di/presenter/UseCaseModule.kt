package android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.presenter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.TimeUtility
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.DataManager
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article.ArticleUseCaseImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.article.ArticlesUseCase
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source.SourceUseCase
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.usecases.source.SourceUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule{

    @Provides
    fun getArticlesUseCase(dataManager: DataManager, timeUtility: TimeUtility) : ArticlesUseCase {
        return ArticleUseCaseImpl(dataManager,timeUtility)
    }

    @Provides
    fun getSourcesUseCase(dataManager: DataManager) : SourceUseCase{
        return SourceUseCaseImpl(dataManager)
    }

}