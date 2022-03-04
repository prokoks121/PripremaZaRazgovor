package com.example.pripremazarazgovor.repository

import android.util.Log
import com.example.pripremazarazgovor.models.NamedApiResourceList
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import com.example.pripremazarazgovor.retrofit.Connect
import retrofit2.Call
import javax.inject.Inject


class Repository @Inject constructor(private val retrofit: Connect) {

   fun getPokemonNamedApiList(
        offset:Int,
        limit:Int
    ):  NamedApiResourceList? {
           val data = retrofit.retrofitApiInterface.getListOfPokemons(offset,limit).execute()
            if (data.isSuccessful) {
                return data.body()
            }
        return null
    }

     fun getPokemon(
        id:Int
    ):Pokemon?{
        val data = retrofit.retrofitApiInterface.getPokemon(id).execute()
        if (data.isSuccessful){
            return  data.body()
        }
        return null

    }


     fun getPokemonSpecies(
        id:Int
    ):PokemonSpecies?{

        val data = retrofit.retrofitApiInterface.getPokemonSpecies(id).execute()
        if (data.isSuccessful){
            return  data.body()
        }
        return null
    }

    private fun urlToId(url: String): Int {
        return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
    }


}