package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.model.RelatedCollectionModel_
import com.github.jasonhezz.likesplash.data.model.TagModel_
import com.github.jasonhezz.likesplash.data.model.photoDetail
import com.github.jasonhezz.likesplash.data.model.title
import com.github.jasonhezz.likesplash.util.extension.carousel
import com.github.jasonhezz.likesplash.util.extension.withModelsFrom

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoDetailController : TypedEpoxyController<Photo>() {

  override fun buildModels(data: Photo?) {
    data?.let {
      photoDetail {
        id(it.id)
        photo(it)
      }

      it.tags?.let {
        title {
          id("tag_title")
          title("Related tags")
        }
        carousel {
          id("tag_carousel")
          paddingDp(16)
          withModelsFrom(it) {
            TagModel_().id(it.title?:"").tag(it)
          }
        }
      }

      it.relatedCollections?.let {
        title {
          id("collection_title")
          title("Featured in ${it.total ?: 0} photos")
        }
        it.results?.let {
          carousel {
            id("collection_carousel")
            numViewsToShowOnScreen(2.5f)
            paddingDp(16)
            withModelsFrom(it) {
              RelatedCollectionModel_().id(it.id).collection(it)
            }
          }
        }
      }
    }
  }
}

