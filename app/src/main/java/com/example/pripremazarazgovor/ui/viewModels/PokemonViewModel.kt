package com.example.pripremazarazgovor.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import com.example.pripremazarazgovor.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    val pokemon by lazy {
        MutableLiveData<Pokemon>()
    }
    val pokemonSpecies by lazy {
        MutableLiveData<PokemonSpecies>()
    }

    var pokemonId:Int = 1

   fun sendRequests(){

       CoroutineScope(Dispatchers.IO).launch {

               val newPokemon = repository.getPokemon(pokemonId)
               newPokemon?.let {
                   withContext(Dispatchers.Main){
                       pokemon.value = it
                   }
               }

           val newPokemonSpecies = repository.getPokemonSpecies(pokemonId)
           newPokemonSpecies?.let {
               withContext(Dispatchers.Main){
                   pokemonSpecies.value = it
               }
           }

       }


    }




}