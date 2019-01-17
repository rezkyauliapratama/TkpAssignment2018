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
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import java.util.ArrayList
/**
 * Created by Rezky Aulia Pratama on 28/10/18.
 */
class RadioGroup : RadioGroup {
    private var radioButtons: ArrayList<RadioButton>? = null
    private var listener: RadioGroup.OnCheckedChangeListener? = null
    var selected: RadioButton? = null
    private val selectedId: Int = 0
    private var trigger: Boolean = false

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        radioButtons = ArrayList()

        search(context, this)
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        search(context, child)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        trigger = false
        if (selected != null) {
            selected?.isChecked = true
            //            radioButtons.get(selectedId).setChecked(true);
        }
        trigger = true
    }

    private fun search(context: Context, view: View) {
        if (view is RadioButton) {
            view.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    clearCheck(view)
                    selected = view
                    if (trigger)
                        listener?.onCheckedChanged(this@RadioGroup, buttonView.id)
                }
            }
            radioButtons?.add(view)
        } else if (view is ViewGroup) {
            search(context, view)
        }
    }

    private fun search(context: Context, viewGroup: ViewGroup) {
        for (i in 0 until viewGroup.childCount) {
            val view = viewGroup.getChildAt(i)
            search(context, view)
        }
    }

    private fun clearCheck(radioButton: RadioButton) {
        radioButtons?.let {
            for (_radioButton in it) {
                if (radioButton !== _radioButton)
                    _radioButton.isChecked = false
            }
        }

    }


    override fun clearCheck() {
        radioButtons?.let {
            for (radioButton in it) {
            radioButton.isChecked = false
        }
        }

    }

    override fun setOnCheckedChangeListener(listener: RadioGroup.OnCheckedChangeListener) {
        this.listener = listener
        super.setOnCheckedChangeListener(listener)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        //        for (RadioButton radioButton : radioButtons)
        //            radioButton.setEnabled(enabled);
        if (!enabled)
            setOnTouchListener { view, motionEvent -> true }
        else
            setOnTouchListener(null)
    }

}
