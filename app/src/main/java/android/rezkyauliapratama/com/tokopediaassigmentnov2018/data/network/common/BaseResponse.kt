package android.rezkyauliapratama.com.tokopediaassigmentnov2018.data.network.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseResponse(
    open var code: String,
    open var message: String,
    open var status: String
) : Parcelable