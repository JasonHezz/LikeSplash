package com.github.jasonhezz.likesplash.ui.controller

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.model.TagModel_
import com.github.jasonhezz.likesplash.data.model.title
import com.github.jasonhezz.likesplash.util.extension.carousel
import com.github.jasonhezz.likesplash.util.extension.withModelsFrom

/**
 * Created by JavaCoder on 2017/10/16.
 */
class TagController(val context: Context) : TypedEpoxyController<List<Tag>>() {


  override fun buildModels(tags: List<Tag>?) {

    title {
      id("tag_title")
      title("Related tags")
    }

    tags?.let {
      carousel {
        id("tag_carousel")
        paddingDp(8)
        withModelsFrom(it) {
          TagModel_().id(it.title).tag(it)
        }
      }
    }

    title {
      id("collection_title")
      title("Featured in 22 collections")
    }


    carousel {
      id("collection_carousel")
      initialPrefetchItemCount(Math.round(2.5f))
    }
  }
}