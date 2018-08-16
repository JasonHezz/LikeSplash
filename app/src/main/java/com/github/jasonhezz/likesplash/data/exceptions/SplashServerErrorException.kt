package com.github.jasonhezz.likesplash.data.exceptions

class SplashServerErrorException(override val code: String, override val message: String?) : SplashException(code, message)