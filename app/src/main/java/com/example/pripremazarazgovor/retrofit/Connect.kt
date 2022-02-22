package com.example.pripremazarazgovor.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Connect {

    val url = "https://pokeapi.co/api/v2/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val retrofitApiInterface by lazy {
        retrofit.build()
            .create(ApiConnect::class.java)
    }

}