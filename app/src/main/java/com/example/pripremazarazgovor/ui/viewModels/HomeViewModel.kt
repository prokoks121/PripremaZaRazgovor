package com.example.pripremazarazgovor.ui.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pripremazarazgovor.repository.Repository
import com.example.pripremazarazgovor.models.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel(),LifecycleObserver {

        val listOfPokemons by lazy {
            MutableLiveData<ArrayList<Pokemon>>(arrayListOf())
        }

    fun updateListOfPokemons(){

        CoroutineScope(Dispatchers.IO).launch {
            val pokemons = listOfPokemons.value ?: arrayListOf()
            val namedApiResourceList = repository.getPokemonNamedApiList(offset,limit)
            namedApiResourceList?.let { apiResourceList ->
                apiResourceList.results.forEach {
                    val pokemon = repository.getPokemon(urlToId(it.url))
                    pokemon?.let { poke->
                        pokemons.add(poke)
                        withContext(Dispatchers.Main){
                            listOfPokemons.value = pokemons
                        }
                    }
                }
            }

            offset += limit
            offset++
        }


    }

    private var offset = 0
    private val limit = 26

    private fun urlToId(url: String): Int {
        return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
    }




}