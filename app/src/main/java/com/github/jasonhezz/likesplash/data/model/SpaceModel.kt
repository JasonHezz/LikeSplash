package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder

@EpoxyModelClass(layout = R.layout.item_space)
abstract class SpaceModel : EpoxyModelWithHolder<BaseViewHolder>()