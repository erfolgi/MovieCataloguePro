package com.erfolgi.moviecataloguepro.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object{
        fun <S> getClient(service:Class<S>) : S{
            val baseURL = "https://api.themoviedb.org/3/"
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service)
        }
    }
}
