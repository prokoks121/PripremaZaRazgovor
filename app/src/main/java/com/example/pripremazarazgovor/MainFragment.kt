package com.example.pripremazarazgovor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pripremazarazgovor.adapters.HomeAdapter
import com.example.pripremazarazgovor.models.HomeModel


class MainFragment : Fragment() {

    val listaPokemon = arrayListOf<HomeModel.Pokemon>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generatePokemons()

        val recList = arrayListOf<HomeModel>(
            HomeModel.Header("Pokemoni")
        )
        recList.addAll(listaPokemon)
        recList.add(HomeModel.Button("Prikazi vise"))
        val recycle = view.findViewById<RecyclerView>(R.id.homeRecycle)
        val adapter = HomeAdapter(recList,this)

        recycle.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(),2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(adapter.listaPokemona[position]){
                    is HomeModel.Header -> 2
                    is HomeModel.Button -> 2
                    else -> 1
                }
            }

        }
        recycle.layoutManager = layoutManager

    }




    fun generatePokemons(){
        listaPokemon.add(
            HomeModel.Pokemon(
                "Bulbasaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/001.png"
            )
        )

        listaPokemon.add(
            HomeModel.Pokemon(
                "Ivysaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/002.png"
            )
        )

        listaPokemon.add(
            HomeModel.Pokemon(
                "Venusaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/003.png"
            )
        )


        listaPokemon.add(
            HomeModel.Pokemon(
                "Charmander",
                "Fire",
                "https://www.serebii.net/pokemongo/pokemon/004.png"
            )
        )


        listaPokemon.add(
            HomeModel.Pokemon(
                "Charmeleon",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/005.png"
            )
        )


        listaPokemon.add(
            HomeModel.Pokemon(
                "Ivysaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/006.png"
            )
        )

        listaPokemon.add(
            HomeModel.Pokemon(
                "Bulbasaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/007.png"
            )
        )

        listaPokemon.add(
            HomeModel.Pokemon(
                "Ivysaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/008.png"
            )
        )

        listaPokemon.add(
            HomeModel.Pokemon(
                "Venusaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/009.png"
            )
        )


        listaPokemon.add(
            HomeModel.Pokemon(
                "Charmander",
                "Fire",
                "https://www.serebii.net/pokemongo/pokemon/010.png"
            )
        )


        listaPokemon.add(
            HomeModel.Pokemon(
                "Charmeleon",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/011.png"
            )
        )


        listaPokemon.add(
            HomeModel.Pokemon(
                "Ivysaur",
                "Grass",
                "https://www.serebii.net/pokemongo/pokemon/012.png"
            )
        )
    }

}