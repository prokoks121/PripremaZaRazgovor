package com.example.pripremazarazgovor.ui.epoxy.controlers

import com.airbnb.epoxy.EpoxyController
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies
import com.example.pripremazarazgovor.ui.epoxy.models.pokemonAbout
import com.example.pripremazarazgovor.ui.epoxy.models.pokemonStats

class PokemonController:EpoxyController() {
    private lateinit var pokemon:Pokemon
    private lateinit var pokemonSpecies: PokemonSpecies
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
        }
    }
}