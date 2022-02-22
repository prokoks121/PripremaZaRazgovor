package com.example.pripremazarazgovor.retrofit

import com.example.pripremazarazgovor.models.NamedApiResource
import com.example.pripremazarazgovor.models.NamedApiResourceList
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiConnect {

    //https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/010.png

    @GET("pokemon/")
    fun getListOfPokemons(
        @Query("offset") offset:Int,
        @Query("limit") limit:Int
    ): Call<NamedApiResourceList>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(
        @Path("id") id:Int
    ):Call<PokemonSpecies>

    @GET("pokemon/{id}")
    fun getPokemon(
        @Path("id") id:Int
    ):Call<Pokemon>

}