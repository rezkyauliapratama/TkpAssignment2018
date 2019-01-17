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

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import com.google.android.material.tabs.TabLayout

/**
 * Created by Rezky Aulia Pratama on 28/10/18.
 */
class TabLayout : TabLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun addTab(@NonNull tab: Tab, position: Int, setSelected: Boolean) {
        changeFont(tab)
        super.addTab(tab, position, setSelected)
    }

    private fun changeFont(tab: Tab) {
        if (tab.text != null) {
            val layout = TabView(getContext())
            tab.text?.let { layout.setTitle(it) }
            tabTextColors?.let { layout.setTextColors(it) }

            tab.customView = layout
        }
    }

}
