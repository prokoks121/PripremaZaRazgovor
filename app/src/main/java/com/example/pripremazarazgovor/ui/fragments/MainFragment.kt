package com.example.pripremazarazgovor.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.ui.epoxy.callBack.HomeCallBack
import com.example.pripremazarazgovor.ui.epoxy.controlers.HomeController
import com.example.pripremazarazgovor.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(),HomeCallBack {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var sortButton:ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val epoxyController = HomeController(this)
        val recycle = view.findViewById<EpoxyRecyclerView>(R.id.homeRecycle)
        val layoutManager = GridLayoutManager(requireContext(),2)
        sortButton = view.findViewById(R.id.sortButton)
        if (viewModel.listOfPokemons.value?.isEmpty() == true){
            viewModel.updateListOfPokemons()
        }
        viewModel.listOfPokemons.observe(viewLifecycleOwner) {
            epoxyController.pokemons = it
        }

        recycle.layoutManager = layoutManager
        recycle.setController(epoxyController)

        sortButton.setOnClickListener {

        }

    }

    override fun getMore() {
        viewModel.updateListOfPokemons()
    }

    override fun onPokemonTouch(id:Int) {
        val action = MainFragmentDirections.actionMainFragment3ToPokemonFragment(id)
        findNavController().navigate(action)

    }


}