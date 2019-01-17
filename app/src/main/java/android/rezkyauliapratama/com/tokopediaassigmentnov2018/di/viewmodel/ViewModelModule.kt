package android.rezkyauliapratama.com.tokopediaassigmentnov2018.di.viewmodel
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleViewModelImpl
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailViewModel
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.sourcepage.controller.MainViewModelImpl
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModelImpl::class)
    abstract fun bindMainViewModel(mainViewModelImpl: MainViewModelImpl) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModelImpl::class)
    abstract fun bindArticleViewModel(articleViewModelImpl: ArticleViewModelImpl) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailviewModel: DetailViewModel) : ViewModel

}