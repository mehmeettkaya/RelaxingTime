package com.mehmetkaya.relaxingtime.exts

import dagger.Lazy
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * This will initialize the OkHttp client lazily during the call, which will be executed only on a background thread.
 */
fun Retrofit.Builder.lazyClient(client: Lazy<OkHttpClient>): Retrofit.Builder {
    return callFactory { request -> client.get().newCall(request) }
}
