package com.github.jasonhezz.likesplash.data.viewholder

import android.support.annotation.CallSuper
import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by JavaCoder on 2017/10/16.
 */
abstract class BaseViewHolder : EpoxyHolder(), LayoutContainer {

  //make it private so that child can't find
  private var containerViewBridge: View? = null

  //rewrite containerView get method
  override val containerView: View?
    get() = containerViewBridge

  @CallSuper
  override fun bindView(itemView: View?) {
    containerViewBridge = itemView
  }
}