package com.github.jasonhezz.likesplash.data.exceptions

class SplashAuthenticationFailedException(override val code: String, override val message: String?) : SplashException(code, message)