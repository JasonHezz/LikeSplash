package com.github.jasonhezz.likesplash.data.exceptions

import java.io.IOException

open class SplashException(open val code: String, override val message: String?) : IOException()