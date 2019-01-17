package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ArticleSchema(
    val author : String,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String,
    var publishedAt : String
) : Parcelable