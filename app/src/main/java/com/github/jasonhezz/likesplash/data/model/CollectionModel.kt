package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.model.CollectionHolder

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_collection)
abstract class CollectionModel : EpoxyModelWithHolder<CollectionHolder>() {

  @EpoxyAttribute
  var collection: Collection? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var avatarClickListener: View.OnClickListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var collectionClickListener: View.OnClickListener? = null

  override fun bind(holder: CollectionHolder?) {
    super.bind(holder)
    collection?.let { holder?.bind(it, avatarClickListener, collectionClickListener) }
  }
}