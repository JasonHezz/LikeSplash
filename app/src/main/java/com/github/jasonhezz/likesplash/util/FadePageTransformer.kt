package com.github.jasonhezz.likesplash.util

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by JavaCoder on 2017/11/28.
 */
class FadePageTransformer : ViewPager.PageTransformer {
  override fun transformPage(
    page: View,
    position: Float
  ) =
    if (position <= -1.0F || position >= 1.0F) {
      page.translationX = page.width * position
      page.setAlpha(0.0F)
    } else if (position == 0.0F) {
      page.translationX = page.width * position
      page.setAlpha(1.0F)
    } else {
      // position is between -1.0F & 0.0F OR 0.0F & 1.0F
      page.translationX = page.width * -position
      page.setAlpha(1.0F - Math.abs(position))
    }
}