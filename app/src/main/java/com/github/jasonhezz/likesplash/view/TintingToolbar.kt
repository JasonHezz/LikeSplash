/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jasonhezz.likesplash.view

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import androidx.core.content.res.getColorOrThrow
import androidx.core.view.forEach
import com.github.jasonhezz.likesplash.R

class TintingToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {

    var iconTint: Int = 0
        set(value) {
            if (value != field) {
                navigationIcon = navigationIcon?.let {
                    it.mutate().apply {
                        setTint(value)
                    }
                }
                overflowIcon = overflowIcon?.let {
                    it.mutate().apply {
                        setTint(value)
                    }
                }
            }
            field = value
        }

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.TintingToolbar, defStyleAttr, 0)
        iconTint = a.getColorOrThrow(R.styleable.TintingToolbar_tintIconColor)
        a.recycle()
    }

    override fun inflateMenu(resId: Int) {
        super.inflateMenu(resId)
        menu.forEach {
            it.icon.mutate().apply {
                setTint(iconTint)
            }
        }
    }
}