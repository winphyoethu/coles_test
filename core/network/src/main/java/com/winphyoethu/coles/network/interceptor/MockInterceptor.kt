package com.winphyoethu.coles.network.interceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return if (request.url.encodedPath.contains("get_recipe")) {
            val json = context.assets.open("recipe.json").bufferedReader().use { it.readText() }
            Response.Builder()
                .request(request)
                .code(200)
                .message(json)
                .protocol(Protocol.HTTP_2)
                .body(json.toResponseBody("application/json".toMediaType()))
                .build()
        } else {
            chain.proceed(request)
        }
    }

}