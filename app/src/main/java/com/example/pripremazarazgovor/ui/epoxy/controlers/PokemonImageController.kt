package com.example.pripremazarazgovor.ui.epoxy.controlers

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyViewHolder
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.ui.epoxy.models.pokemonImg

class PokemonImageController(val setPosition:()->Unit):EpoxyController() {

    var pokemons:ArrayList<Pokemon> = arrayListOf()
    set(value) {
        field = value
        requestModelBuild()
    }
    private var check = true


    override fun onViewAttachedToWindow(holder: EpoxyViewHolder, model: EpoxyModel<*>) {
        super.onViewAttachedToWindow(holder, model)
        if (check){
            check = false
            setPosition()
        }
    }


    override fun buildModels() {
        pokemons.forEach {
            pokemonImg {
                id(it.id)
                pokemonId(it.id.toString())
            }
        }
    }
}