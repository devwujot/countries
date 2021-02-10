package com.decwujot.countires.framework.network

import android.content.Context
import com.decwujot.countires.framework.utils.getJsonDataFromAsset
import okhttp3.*

class MockInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("all") -> getJsonDataFromAsset(context, "countries.json")
            uri.endsWith("alpha/AF") -> getJsonDataFromAsset(context, "country.json")
            else -> "{}"
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString!!.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}
