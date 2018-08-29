package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.epoxy.model.createCollection
import com.github.jasonhezz.likesplash.ui.epoxy.model.dialogCollection
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty

/**
 * Created by JavaCoder on 2017/10/16.
 */

class DialogCollectionController(
    var callback: AdapterCallbacks? = null
) : AsyncEpoxyController() {

    var collections by EpoxyModelProperty { emptyList<Collection>() }

    override fun buildModels() {
        createCollection {
            id("create_collection")
            onClickListener { model, parentView, clickedView, position ->
                callback?.onCreateCollectionClick()
            }
        }
        collections.forEachIndexed { index, collection ->
            dialogCollection {
                id(index)
                collection(collection)
                collectionClickListener { model, parentView, clickedView, position ->
                    callback?.onCollectionClick()
                }
            }
        }
    }

    interface AdapterCallbacks {
        fun onCollectionClick()
        fun onCreateCollectionClick()
    }
}

