package com.github.jasonhezz.likesplash.data.api

/**
 * Created by JavaCoder on 2017/11/28.
 */
data class Resource(val status: Status, val message: String? = null) {
  companion object {
    val LOADED = Resource(Status.SUCCESS)
    val INITIAL = Resource(Status.REFRESHING)
    val MORE = Resource(Status.LOADING_MORE)
    fun error(msg: String?) = Resource(Status.ERROR, msg)
  }
}