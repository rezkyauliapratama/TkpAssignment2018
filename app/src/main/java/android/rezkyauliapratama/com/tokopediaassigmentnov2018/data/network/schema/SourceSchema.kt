package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.schema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SourceSchema(
    var id : String,
    var name : String,
    var description : String,
    var url : String,
    var category : String,
    var language : String,
    var country : String
) : Parcelable