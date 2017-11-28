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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.jasonhezz.likesplash.util.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.UiThread
import android.util.TypedValue
import android.view.View


/**
 * Queries on-device packages for a handler for the supplied [Intent].
 */
private fun Context.hasHandler(intent: Intent) =
    !packageManager.queryIntentActivities(intent, 0).isEmpty()

private val typedValue = TypedValue()

@ColorInt
@UiThread
fun Context.resolveAttribute(@AttrRes resId: Int): Int {
  theme.resolveAttribute(resId, typedValue, true)
  return typedValue.data
}

fun Context.isInNightMode(): Boolean {
  val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
  return when (currentNightMode) {
    Configuration.UI_MODE_NIGHT_NO -> false // Night mode is not active, we're in day time
    Configuration.UI_MODE_NIGHT_YES -> true // Night mode is active, we're at night!
    else -> false // We don't know what mode we're in, assume notnight
  }
}

/**
 * Determine if the navigation bar will be on the bottom of the screen, based on logic in
 * PhoneWindowManager.
 */
fun Context.isNavBarOnBottom(): Boolean {
  val res = resources
  val cfg = resources.configuration
  val dm = res.displayMetrics
  val canMove = dm.widthPixels != dm.heightPixels && cfg.smallestScreenWidthDp < 600
  return !canMove || dm.widthPixels < dm.heightPixels
}

inline fun Context.dp2px(dipValue: Float) = resources.dp2px(dipValue)

inline fun Resources.dp2px(dipValue: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, displayMetrics)

inline fun View.dp2px(dipValue: Float) = context.dp2px(dipValue)


fun Context.getActivity(): Activity? {
  if (this is Activity) return this
  if (this is ContextWrapper) {
    return this.baseContext.getActivity()
  }
  return null
}

val Context.isTranslucentStatus: Boolean
  get() {
    return when {
      null == this.getActivity() -> false
      Build.VERSION.SDK_INT >= 19 -> (this.getActivity()!!.window.attributes.flags and 67108864) == 67108864
      else -> false
    }
  }

val Context.isTranslucentNav: Boolean
  get() {
    return when {
      null == this.getActivity() -> false
      Build.VERSION.SDK_INT >= 19 -> (this.getActivity()!!.window.attributes.flags and 134217728) == 134217728
      else -> false
    }
  }

