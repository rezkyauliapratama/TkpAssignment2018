/*
        Copyright [2018] [Rezky Aulia Pratama]

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.*/


package android.beautyview.basic

import android.annotation.SuppressLint
import android.beautyview.R
import android.beautyview.utility.BeautyView
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

/**
 * Created by Rezky Aulia Pratama on 28/10/18.
 */
class RadioButton : AppCompatRadioButton {
    constructor(context: Context) : super(context) {
        setCustomTypeface(context, null)

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCustomTypeface(context, attrs)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setCustomTypeface(context, attrs)

    }


    @SuppressLint("CustomViewStyleable", "PrivateResource")
    private fun setCustomTypeface(context: Context, attrs: AttributeSet?) {
        if (isInEditMode)
            return
        val a = context.obtainStyledAttributes(attrs, R.styleable.TextAppearance)
        val style = a.getInt(R.styleable.TextAppearance_android_textStyle, Typeface.NORMAL)

        setTextStyle(style)
        a.recycle()

    }


    fun setTextStyle(style: Int) {
        BeautyView.setTextStyle(this, style)
    }

    fun setSupportTextAppearance(resId: Int) {
        if (Build.VERSION.SDK_INT >= 23)
            this.setTextAppearance(resId)
        else
            this.setTextAppearance(context, resId)
    }

}
