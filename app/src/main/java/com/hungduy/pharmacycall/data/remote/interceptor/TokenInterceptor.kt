package com.hungduy.pharmacycall.data.remote.interceptor

import com.hungduy.pharmacycall.util.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenStorage: TokenStorage
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().apply {
            tokenStorage.getToken()?.let { addHeader("token", it) }
        }.build()
        return chain.proceed(req)
    }
}
