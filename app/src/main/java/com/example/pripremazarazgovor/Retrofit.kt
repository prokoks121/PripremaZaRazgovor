package com.example.pripremazarazgovor

import retrofit2.converter.gson.GsonConverterFactory




class Retrofit {

    val url = "https://bekmen.rs/api/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val retrofitApiInterface by lazy {
        retrofit.build()
            .create(ApiConntect::class.java)
    }

}