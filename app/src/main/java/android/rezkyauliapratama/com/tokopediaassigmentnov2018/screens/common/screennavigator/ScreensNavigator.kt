package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.screennavigator

import android.app.Activity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.controller.ArticleActivity
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.fragmentframehelper.FragmentFrameHelper
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.detailpage.DetailActivity
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity


class ScreensNavigator(private val fragmentFrameHelper: FragmentFrameHelper?, private val activity: Activity) {

    fun toArticleActivity(url: String, name: String){
        activity.ctx.startActivity<ArticleActivity>("data".to(arrayOf(url,name)))
    }

    fun toDetailActivity(url: String, title : String) {
        activity.ctx.startActivity<DetailActivity>("data".to(arrayOf(url,title)))
    }

}
