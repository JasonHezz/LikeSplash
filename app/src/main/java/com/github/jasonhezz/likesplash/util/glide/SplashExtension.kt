package com.github.jasonhezz.likesplash.util.glide

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.annotation.GlideType
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.jasonhezz.likesplash.util.extension.hexToMaterialHex

/**
 * Created by JavaCoder on 2017/12/14.
 */
@GlideExtension
object SplashExtension {

    @GlideOption
    @JvmStatic
    fun materialPlaceHolder(options: RequestOptions, color: String): RequestOptions {
        return options.placeholder(ColorDrawable(color.hexToMaterialHex()))
    }

    @JvmStatic
    @GlideType(Drawable::class)
    fun saturateOnLoad(requestBuilder: RequestBuilder<Drawable>) {
        requestBuilder.transition(DrawableTransitionOptions.with(SaturationTransitionFactory()))
    }
}

