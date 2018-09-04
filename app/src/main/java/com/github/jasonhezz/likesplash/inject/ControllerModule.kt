package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.ui.epoxy.controller.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val controllerModule = module {
    factory { (callback: CollectionPagedController.AdapterCallbacks) ->
        CollectionPagedController(callback).also { it.setFilterDuplicates(true) }
    }
    factory { (callback: DialogCollectionController.AdapterCallbacks) ->
        DialogCollectionController(callback).also { it.setFilterDuplicates(true) }
    }
    factory { (callback: PhotoPagedController.AdapterCallbacks) ->
        PhotoPagedController(callback).also { it.setFilterDuplicates(true) }
    }
    factory {
        PhotoDetailController(androidContext())
    }
    factory {
        PopularCollectionController(androidContext()).also { it.setFilterDuplicates(true) }
    }
    factory {
        PopularPhotoController(androidContext()).also { it.setFilterDuplicates(true) }
    }
    factory { (callback: PreviewCollectionPagedController.AdapterCallbacks) ->
        PreviewCollectionPagedController(androidContext(), callback).also { it.setFilterDuplicates(true) }
    }
    factory {
        SearchController(androidContext())
    }
}