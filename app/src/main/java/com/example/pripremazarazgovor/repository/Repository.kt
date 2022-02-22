package com.example.pripremazarazgovor.repository

import com.example.pripremazarazgovor.models.NamedApiResourceList
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import com.example.pripremazarazgovor.retrofit.Connect
import retrofit2.Call
import javax.inject.Inject


class Repository @Inject constructor(private val retrofit: Connect) {

    fun getPokemons(
        offset:Int,
        limit:Int
    ):Call<NamedApiResourceList>{
        return retrofit.retrofitApiInterface.getListOfPokemons(offset,limit)
    }

    fun getPokemon(
        id:Int
    ):Call<Pokemon>{
return retrofit.retrofitApiInterface.getPokemon(id)
    }


    fun getPokemonSpecies(
        id:Int
    ):Call<PokemonSpecies>{
        return retrofit.retrofitApiInterface.getPokemonSpecies(id)
    }

    private fun urlToId(url: String): Int {
        return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
    }


}