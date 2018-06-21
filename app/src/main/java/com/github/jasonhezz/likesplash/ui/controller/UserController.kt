package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.user

/**
 * Created by JavaCoder on 2017/12/8.
 */
class UserController : EpoxyController() {
  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var users = emptyList<User>()
    set(value) {
      field = value
      requestModelBuild()
    }

  var isLoading: Boolean = false
    set(value) {
      if (field != value) {
        field = value
        requestModelBuild()
      }
    }

  override fun buildModels() {
    users.forEach {
      user {
        id(it.id?:"")
        user(it)
      }
    }
    loadingModel.addIf(isLoading, this)
  }
}