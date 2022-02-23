package com.example.pripremazarazgovor.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.ui.epoxy.controlers.PokemonController
import com.example.pripremazarazgovor.ui.viewModels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    val viewModel:PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = PokemonController()
        val recyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setController(epoxyController)

        viewModel.pokemonId = 5
        viewModel.sendRequests()
        viewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemon->
            viewModel.pokemonSpecies.value?.let { pokemonSpecies->
                epoxyController.setValues(pokemon = pokemon, pokemonSpecies = pokemonSpecies)
            }
        })

        viewModel.pokemonSpecies.observe(viewLifecycleOwner, Observer {  pokemonSpecies->
            viewModel.pokemon.value?.let { pokemon->
                epoxyController.setValues(pokemon = pokemon, pokemonSpecies = pokemonSpecies)
            }
        })

    }



}