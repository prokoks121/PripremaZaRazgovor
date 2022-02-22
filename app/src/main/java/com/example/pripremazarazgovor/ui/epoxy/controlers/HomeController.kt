package com.example.pripremazarazgovor.ui.epoxy.controlers

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.example.pripremazarazgovor.ui.epoxy.callBack.HomeCallBack
import com.example.pripremazarazgovor.ui.epoxy.models.homeUcitaj
import com.example.pripremazarazgovor.ui.epoxy.models.pokemon
import com.example.pripremazarazgovor.models.Pokemon

class HomeController(val callBack:HomeCallBack):EpoxyController(){

    var pokemons = arrayListOf<Pokemon>()
    set(value) {
        field = value
        requestModelBuild()
    }


    override fun buildModels() {

        pokemons.forEach {
            pokemon {
                id(it.id)
                name(it.name)
                type(it.types[0].type.name)
                pokemonId(it.id.toString())
                color(it.color)
                if (it.types.size > 1){
                    type2(it.types[1].type.name)
                }
            }
        }

        homeUcitaj {
            id("ucitaj_jos")
            listener(View.OnClickListener {
                this@HomeController.callBack.getMore()
            })

            spanSizeOverride { _, _, _ ->
                2
                 }
        }
    }


}