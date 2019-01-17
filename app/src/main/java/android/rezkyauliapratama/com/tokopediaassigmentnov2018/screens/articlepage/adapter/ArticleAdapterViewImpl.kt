package android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.articlepage.adapter

import android.rezkyauliapratama.com.tokopediaassigmentnov2018.BR
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.R
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.common.DimensionConverter
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema.ArticleSchema
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.databinding.ListItemArticleBinding
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.tokopediaassigmentnov2018.screens.common.views.BaseObservableView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso


class ArticleAdapterViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseObservableView<ArticleAdapterView.Listener>() , ArticleAdapterView{


    var binding: ListItemArticleBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_article,parent,false)

    init {
        dataBinding = binding
    }

    override fun bindList(articleSchema: ArticleSchema) {

        binding.root.setOnClickListener {
            for(listener in listeners){
                listener.onItemclicked(articleSchema.url,articleSchema.title)
            }
        }

        val width =
            getContext()?.resources?.displayMetrics?.let {
                DimensionConverter.stringToDimension("92dp",
                    it
                ).toInt()
            }
        width?.let {
            Picasso
                .get()
                .load(articleSchema.urlToImage)
                .placeholder(R.drawable.ic_image)
                .fit()
                .into(binding.ivPicture)
        }

        binding.setVariable(BR.article,articleSchema)
        binding.executePendingBindings()

    }

}