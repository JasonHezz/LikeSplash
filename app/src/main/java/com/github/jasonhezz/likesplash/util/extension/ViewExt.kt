/*
 * Copyright (c) 2017 Zac Sweers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jasonhezz.likesplash.util.extension

import android.os.Build
import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.jasonhezz.likesplash.util.glide.GlideApp


fun View.setLightStatusBar() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    var flags = systemUiVisibility
    // TODO noop if it's already set
    flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    systemUiVisibility = flags
  }
}

fun View.clearLightStatusBar() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    var flags = systemUiVisibility
    // TODO noop if it's already not set
    flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    systemUiVisibility = flags
  }
}

fun View.setLightNavBar() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    var flags = systemUiVisibility
    // TODO noop if it's already set
    flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    systemUiVisibility = flags
  }
}

fun View.clearLightNavBar() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    var flags = systemUiVisibility
    // TODO noop if it's already not set
    flags = flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
    systemUiVisibility = flags
  }
}

//support library 25.3.1 is different from 26.1.0 under lolipop
fun View.showSnackbar(snackbarText: String, timeLength: Int = 250) {
  Snackbar.make(this, snackbarText, timeLength).show()
}

inline fun View.isVisible() = visibility == View.VISIBLE

inline fun View.show() {
  visibility = View.VISIBLE
}

infix inline fun View.showIf(condition: Boolean) {
  if (condition) {
    show()
  } else {
    hide()
  }
}

inline fun View.isGone() = visibility == View.GONE
inline fun View.hide() {
  visibility = View.GONE
}

inline fun View.marginTop(top: Int) {
  val lp = this.layoutParams as ViewGroup.MarginLayoutParams
  lp.topMargin += top
  this.layoutParams = lp
}

inline fun View.marginBottom(bottom: Int) {
  val lp = this.layoutParams as ViewGroup.MarginLayoutParams
  lp.bottomMargin = bottom
  this.layoutParams = lp
}


infix inline fun View.hideIf(condition: Boolean) {
  if (condition) {
    hide()
  } else {
    show()
  }
}

fun View.doOnLayout(onLayout: (View) -> Boolean) {
  addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
    override fun onLayoutChange(view: View, left: Int, top: Int, right: Int, bottom: Int,
        oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
      if (onLayout(view)) {
        view.removeOnLayoutChangeListener(this)
      }
    }
  })
}


fun ImageView.loadUrl(url: String?, placeholder: Int? = null) {
  GlideApp.with(context).load(url).apply {
    if (placeholder != null) placeholder(placeholder)
  }.into(this)
}


