package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.SimpleEpoxyController
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_explore_tag_carousel.*

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_explore_tag_carousel)
abstract class ExploreCarouselTagModel : EpoxyModelWithHolder<BaseViewHolder>() {
  @EpoxyAttribute
  var tags: List<Tag>? = null

  val controller = SimpleEpoxyController()

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var tagClickListener: View.OnClickListener? = null

  override fun bind(holder: BaseViewHolder) {
    super.bind(holder)
    val models = tags.let {
      it?.map {
        ExploreTagModel_().id(it.url).tag(it)
      }
    } ?: emptyList()
    holder.carousel.isNestedScrollingEnabled = false
    holder.carousel.setController(controller)
    controller.setModels(models)
  }
}