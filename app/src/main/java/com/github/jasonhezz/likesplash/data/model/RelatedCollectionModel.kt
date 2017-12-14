package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.viewholder.RelatedCollectionHolder

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_related_collection)
abstract class RelatedCollectionModel : EpoxyModelWithHolder<RelatedCollectionHolder>() {

  @EpoxyAttribute
  var collection: Collection? = null

  override fun bind(holder: RelatedCollectionHolder?) {
    super.bind(holder)
    collection?.let { holder?.bind(it) }
  }
}