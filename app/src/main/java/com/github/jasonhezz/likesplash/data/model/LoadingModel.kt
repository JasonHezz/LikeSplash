package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.model.LoadingModelHolder

/**
 * Created by JavaCoder on 2017/11/28.
 */
@EpoxyModelClass(layout = R.layout.infinite_loading)
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModelHolder>() {

}