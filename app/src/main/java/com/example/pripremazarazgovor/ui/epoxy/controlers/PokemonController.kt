package com.example.pripremazarazgovor.ui.epoxy.controlers

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyViewHolder
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import com.example.pripremazarazgovor.ui.epoxy.models.pokemonAbout
import com.example.pripremazarazgovor.ui.epoxy.models.pokemonStats

class PokemonController:EpoxyController() {
    private lateinit var pokemon:Pokemon
    private lateinit var pokemonSpecies: PokemonSpecies
    var progressBarStartAnimation = false
    set(value) {
        if (value != field && this::pokemon.isInitialized && this::pokemonSpecies.isInitialized){
            field = value

            requestModelBuild()
        }
    }
    fun setValues(pokemon: Pokemon,pokemonSpecies: PokemonSpecies){
        this.pokemon = pokemon
        this.pokemonSpecies = pokemonSpecies
        requestModelBuild()
    }



    override fun buildModels() {
        pokemonAbout {
            id("about")
            pokemon(this@PokemonController.pokemon)
           pokemonSpecies(this@PokemonController.pokemonSpecies)


        }

        pokemonStats {
            id("stats")
            pokemon(this@PokemonController.pokemon)
            progressBarAnimationStart(this@PokemonController.progressBarStartAnimation)
        }

    }

}