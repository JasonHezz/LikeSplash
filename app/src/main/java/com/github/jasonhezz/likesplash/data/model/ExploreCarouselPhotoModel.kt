package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.SimpleEpoxyController
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_explore_photo_carousel.*

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_explore_photo_carousel)
abstract class ExploreCarouselPhotoModel : EpoxyModelWithHolder<BaseViewHolder>() {
  @EpoxyAttribute
  var photos: List<Photo>? = null
  val controller = SimpleEpoxyController()

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var clickListener: View.OnClickListener? = null

  override fun bind(holder: BaseViewHolder) {
    super.bind(holder)
    val models = photos.let {
      it?.map {
        ExplorePhotoModel_().id(it.id).photo(it).photoClickListener(clickListener)
      }
    } ?: emptyList()
    holder.carousel.setInitialPrefetchItemCount(3)
    holder.carousel.setController(controller)
    controller.setModels(models)
  }
}