package com.example.pripremazarazgovor.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import com.example.pripremazarazgovor.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(val repository: Repository): ViewModel() {


    val pokemon by lazy {
        MutableLiveData<Pokemon>()
    }
    val pokemonSpecies by lazy {
        MutableLiveData<PokemonSpecies>()
    }

    var pokemonId:Int = 1

    fun sendRequests(){
        repository.getPokemon(pokemonId).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {

                pokemon.value = response.body()

            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        repository.getPokemonSpecies(pokemonId).enqueue(object :Callback<PokemonSpecies>{
            override fun onResponse(
                call: Call<PokemonSpecies>,
                response: Response<PokemonSpecies>,
            ) {
                pokemonSpecies.value = response.body()

            }

            override fun onFailure(call: Call<PokemonSpecies>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }




}