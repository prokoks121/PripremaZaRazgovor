package com.example.pripremazarazgovor.ui.epoxy.models

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.pokemon_about)
abstract class PokemonAboutModel:EpoxyModelWithHolder<PokemonAboutModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var pokemon:Pokemon

    @EpoxyAttribute
    lateinit var pokemonSpecies:PokemonSpecies

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        holder.specs.text = pokemonSpecies.growth_rate.name
        holder.height.text = pokemon.height.toString()
        holder.weight.text = pokemon.weight.toString()
        holder.eggGroups.text = pokemon.types[0].type.name
        holder.gander.text = pokemonSpecies.gender_rate.toString()
        var pokemonAbility = ""
        if (pokemon.abilities.size > 1){
            pokemonAbility = "${pokemon.abilities[0].ability.name}, ${pokemon.abilities[1].ability.name}"
        }else if (pokemon.abilities.isNotEmpty()){
            pokemonAbility = pokemon.abilities[0].ability.name
        }
        holder.abilities.text = pokemonAbility
        holder.eggCycle.text = pokemonSpecies.egg_groups[0].name


    }



    inner class ViewHolder:EpoxyHolder(){
        lateinit var specs:TextView
        lateinit var height:TextView
        lateinit var weight:TextView
        lateinit var abilities:TextView
        lateinit var gander:TextView
        lateinit var eggGroups:TextView
        lateinit var eggCycle:TextView


        override fun bindView(itemView: View) {
            specs = itemView.findViewById(R.id.species_data)
            height = itemView.findViewById(R.id.height_data)
            weight = itemView.findViewById(R.id.weight_data)
            abilities = itemView.findViewById(R.id.abilities_data)
            gander = itemView.findViewById(R.id.gander_data)
            eggGroups = itemView.findViewById(R.id.egg_data)
            eggCycle = itemView.findViewById(R.id.egg_cycle_data)

        }

    }
}