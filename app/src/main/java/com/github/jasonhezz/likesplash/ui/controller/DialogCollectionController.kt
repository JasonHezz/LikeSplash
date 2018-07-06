package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.model.createCollection
import com.github.jasonhezz.likesplash.data.model.dialogCollection

/**
 * Created by JavaCoder on 2017/10/16.
 */

class DialogCollectionController(
    var callback: AdapterCallbacks? = null
) : EpoxyController() {

    var collections = emptyList<Collection>()
        set(value) {
            if (field != value) {
                field = value
                requestModelBuild()
            }
        }

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

    companion object {
        interface AdapterCallbacks {
            fun onCollectionClick()
            fun onCreateCollectionClick()
        }
    }
}

