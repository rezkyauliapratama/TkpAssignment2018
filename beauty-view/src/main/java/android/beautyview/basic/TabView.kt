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

import android.beautyview.R
import android.beautyview.utility.BeautyView
import android.content.Context
import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout

/**
 * Created by Rezky Aulia Pratama on 28/10/18.
 */
class TabView : LinearLayout {
    private var newTab: TextView? = null
    private var title: CharSequence? = null
    private var textColors: ColorStateList? = null

    constructor(context: Context) : super(context) {
        init(context, null)

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val layout = this
        layout.orientation = LinearLayout.HORIZONTAL
        layout.gravity = Gravity.CENTER
        newTab = TextView(getContext())
        newTab?.setSupportTextAppearance(R.style.TextAppearance_Design_Tab)
        newTab?.typeface = BeautyView.getBold()
        newTab?.setAllCaps(true)
        newTab?.setGravity(Gravity.CENTER)
        if (textColors != null)
            newTab?.setTextColor(textColors)
        newTab?.setMaxLines(1)
        newTab?.setEllipsize(TextUtils.TruncateAt.END)
        newTab?.setText(title)

        layout.addView(newTab)

    }

    fun setTextColors(textColors: ColorStateList) {
        this.textColors = textColors
        newTab?.setTextColor(textColors)
    }

    fun setTitle(title: CharSequence) {
        this.title = title
        newTab?.text = title
    }

    companion object {

        fun getTabView(tab: TabLayout.Tab): View? {
            return if (tab.customView is TabView)
                tab.customView
            else
                null
        }
    }
}
