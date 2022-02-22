package com.example.pripremazarazgovor.ui.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pripremazarazgovor.repository.Repository
import com.example.pripremazarazgovor.models.NamedApiResourceList
import com.example.pripremazarazgovor.models.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel(),LifecycleObserver {

        val listOfPokemons by lazy {
            MutableLiveData<ArrayList<Pokemon>>(arrayListOf())
        }

    fun updateListOfPokemons(){

        val listOfPokemons = this.listOfPokemons.value ?: arrayListOf()






        val listOfPokemonsUrl = repository.getPokemons(offset,limit)
        listOfPokemonsUrl.enqueue(object : Callback<NamedApiResourceList> {
            override fun onResponse(
                call: Call<NamedApiResourceList>,
                response: Response<NamedApiResourceList>,
            ) {
                response.body()?.let {
                    it.results.forEach { url->

                        repository.getPokemon(urlToId(url.url)).enqueue(object :Callback<Pokemon> {
                            override fun onResponse(
                                call: Call<Pokemon>,
                                response: Response<Pokemon>,
                            ) {
                               response.body()?.let { pokemon->
                        if (listOfPokemons.size != 0){
                            for (i in 0 until listOfPokemons.size){
                                if (listOfPokemons[i].id > pokemon.id){
                                    listOfPokemons.add(i,pokemon)
                                    this@HomeViewModel.listOfPokemons.value = listOfPokemons
                                    return
                                }
                            }
                            listOfPokemons.add(listOfPokemons.size,pokemon)
                        }else{
                            listOfPokemons.add(pokemon)
                        }
                                   this@HomeViewModel.listOfPokemons.value = listOfPokemons
                               }
                            }

                            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }

                }


            }

            override fun onFailure(call: Call<NamedApiResourceList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        offset += limit
        offset++
    }

    private var offset = 0
    private val limit = 26

    private fun urlToId(url: String): Int {
        return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
    }




}