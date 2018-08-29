package com.github.jasonhezz.likesplash

import android.net.Uri
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by JavaCoder on 2017/11/28.
 */
@RunWith(JUnit4::class)
class ParsePageTest {
    @Test
    fun parse() {
        val pageUri = "https://api.unsplash.com/feeds/home?after=92ed71e0-d401-11e7-8080-80001597064c"
        val uri = Uri.parse(pageUri)
        val page = uri.getQueryParameter("after")
        Assert.assertEquals("92ed71e0-d401-11e7-8080-80001597064c", page)
    }
}