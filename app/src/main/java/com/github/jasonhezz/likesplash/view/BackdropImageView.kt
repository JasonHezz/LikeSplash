package com.github.jasonhezz.likesplash.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.support.v4.graphics.ColorUtils
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.widget.ImageView
import com.github.jasonhezz.likesplash.R


/**
 * Created by JavaCoder on 2017/11/27.
 */
class BackdropImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

  private var mScrimDarkness: Float = 0f
  private var mScrimColor = Color.BLACK
  private var mScrollOffset: Int = 0
  private var mImageOffset: Int = 0
  private val mScrimPaint: Paint = Paint()
  private var friction = 0.5f

  init {
    val a = context.obtainStyledAttributes(attrs, R.styleable.BackdropImageView)
    mScrimColor = a.getColor(R.styleable.BackdropImageView_scrimColor, Color.BLACK)
    MIN_SCRIM_ALPHA = a.getInteger(R.styleable.BackdropImageView_minScrim, MIN_SCRIM_ALPHA)
    MAX_SCRIM_ALPHA = a.getInteger(R.styleable.BackdropImageView_maxScrim, MAX_SCRIM_ALPHA)
    SCRIM_ALPHA_DIFF = MAX_SCRIM_ALPHA - MIN_SCRIM_ALPHA
    friction = a.getFloat(R.styleable.BackdropImageView_friction, 0.5f)
    a.recycle()
  }

  fun setScrollOffset(offset: Int) {
    if (offset != mScrollOffset) {
      mScrollOffset = offset
      mImageOffset = -offset / 2
      mScrimDarkness = Math.abs(offset / height.toFloat())
      offsetTopAndBottom(offset - top)
      ViewCompat.postInvalidateOnAnimation(this)
    }
  }

  fun setOffsetFriction(friction: Float) {
    if (friction <= 1 && friction > 0)
      this.friction = friction
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)

    if (mScrollOffset != 0) {
      offsetTopAndBottom(mScrollOffset - getTop())
    }
  }

  fun setScrimColor(@ColorInt scrimColor: Int) {
    if (mScrimColor != scrimColor) {
      mScrimColor = scrimColor
      ViewCompat.postInvalidateOnAnimation(this)
    }
  }

  override fun onDraw(canvas: Canvas) {
    // Update the scrim paint
    mScrimPaint.color = ColorUtils.setAlphaComponent(mScrimColor,
        MIN_SCRIM_ALPHA + (SCRIM_ALPHA_DIFF * mScrimDarkness).toInt())

    if (mImageOffset != 0) {
      canvas.save()
      canvas.translate(0f, mImageOffset.toFloat())
      canvas.clipRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat() + mImageOffset + 1)
      super.onDraw(canvas)
      canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), mScrimPaint)
      canvas.restore()
    } else {
      super.onDraw(canvas)
      canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), mScrimPaint)
    }
  }

  companion object {
    private var MIN_SCRIM_ALPHA = 20
    private var MAX_SCRIM_ALPHA = 180
    private var SCRIM_ALPHA_DIFF = 0
  }
}