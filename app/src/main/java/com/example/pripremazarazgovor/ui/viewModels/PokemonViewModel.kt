package com.example.pripremazarazgovor.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    private var _pokemonId = 1
    val pokemonId:Int
        get() {
       return _pokemonId
    }

    fun changePokemonId(id:Int){
        _pokemonId = id
    }


    private var _imageDefaultCheck = true
    val imageDefaultCheck:Boolean
    get() {
        return _imageDefaultCheck
    }

    fun disableImageCheck(){
        _imageDefaultCheck = false
    }


   fun getSpecies(){

       viewModelScope.launch {
           withContext(Dispatchers.IO) {
               val newPokemonSpecies = repository.getPokemonSpecies(pokemonId+1)
               newPokemonSpecies?.let {
                   withContext(Dispatchers.Main) {
                       pokemonSpecies.value = it
                   }
               }
           }

       }


    }





    val listOfPokemons by lazy {
        MutableLiveData<ArrayList<Pokemon>>(arrayListOf())
    }

    fun getPokemons(){

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pokemons =  arrayListOf<Pokemon>()
                val namedApiResourceList = repository.getPokemonNamedApiList(offset, limit)
                namedApiResourceList?.let { apiResourceList ->
                    apiResourceList.results.forEach {
                        val pokemon = repository.getPokemon(urlToId(it.url))
                        pokemon?.let { poke ->
                            pokemons.add(poke)
                        }
                    }
                    withContext(Dispatchers.Main) {
                        listOfPokemons.value = pokemons
                    }
                }

                //offset += limit
                //offset++
            }
        }

    }

    private var offset = 0
    private val limit = 10


    private fun urlToId(url: String): Int {
        return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
    }

}