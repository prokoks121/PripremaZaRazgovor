package com.example.pripremazarazgovor.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.ui.epoxy.controlers.PokemonController
import com.example.pripremazarazgovor.ui.epoxy.controlers.PokemonImageController
import com.example.pripremazarazgovor.ui.viewModels.PokemonViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private val viewModel:PokemonViewModel by viewModels()
    private val navArgs:PokemonFragmentArgs by navArgs()

    private lateinit var pokemonName:TextView
    private lateinit var pokemonType:TextView
    private lateinit var pokemonType2:TextView
    private lateinit var pokemonId:TextView
    private lateinit var motionLayout: MotionLayout
    private lateinit var speciesController:PokemonController
    private lateinit var speciesViewPager: ViewPager2
    private lateinit var tabLayout:TabLayout
    private lateinit var imgController:PokemonImageController
    private lateinit var imgViewPager:ViewPager2
    private val tabsList = arrayListOf("About","Base Stats","Evolution","Moves")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.changePokemonId(navArgs.pokemonId-1)

        initViews(view)
        initRecycleViews()

        viewModel.getPokemons()
        viewModel.getSpecies()

        viewModel.listOfPokemons.observe(viewLifecycleOwner){
            imgController.pokemons = it
           if (it.size > viewModel.pokemonId) {
               viewModel.pokemon.value = it[viewModel.pokemonId]
           }
        }


       viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
           setDataToView(pokemon)
           viewModel.getSpecies()
           viewModel.pokemonSpecies.value?.let { pokemonSpecies ->
                speciesController.setValues(pokemon = pokemon, pokemonSpecies = pokemonSpecies)
                setTabColor(pokemon.color)
            }
        }

        viewModel.pokemonSpecies.observe(viewLifecycleOwner) { pokemonSpecies ->
            viewModel.pokemon.value?.let { pokemon ->
                speciesController.setValues(pokemon = pokemon, pokemonSpecies = pokemonSpecies)
            }
        }

    }


    private fun initViews(view: View){
        pokemonId = view.findViewById(R.id.b)
        pokemonName = view.findViewById(R.id.pokemonName)
        pokemonType = view.findViewById(R.id.pokemonType)
        pokemonType2 = view.findViewById(R.id.pokemonType2)
        motionLayout = view.findViewById(R.id.pokemonMotion)
        imgViewPager = view.findViewById(R.id.pokemonImgPager)
        speciesViewPager = view.findViewById(R.id.epoxy)
        tabLayout = view.findViewById(R.id.tabL)
    }

    private fun initRecycleViews(){
        initImgView()
        initSpeciesView()
    }


    private fun initImgView(){
        imgController = PokemonImageController{
            imgViewPager.setCurrentItem(viewModel.pokemonId,false)
        }

        imgViewPager.adapter = imgController.adapter
        imgViewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){

            @Override
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                viewModel.listOfPokemons.value?.let {
                    if (viewModel.pokemonId != position) {
                        viewModel.changePokemonId(position)
                        viewModel.pokemon.value = it[viewModel.pokemonId]
                    }
                }
            }
        })
    }

    private fun initSpeciesView(){
        speciesController = PokemonController()
        speciesViewPager.adapter = speciesController.adapter

        speciesViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            @Override
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                speciesController.progressBarStartAnimation = position == 1
            }
        })

        TabLayoutMediator(tabLayout, speciesViewPager){ tab, position ->
            tab.text = tabsList[position]
        }.attach();
    }



    private fun setTabColor(color:String){
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor(color))
    }


    @SuppressLint("SetTextI18n")
    private fun setDataToView(pokemon:Pokemon){
        pokemonId.text = "#${pokemon.id.toString().padStart(3, '0')}"
        pokemonType.text = pokemon.types[0].type.name
        pokemonName.text = pokemon.name
        if (pokemon.types.size>1){
            pokemonType2.text = pokemon.types[1].type.name
            pokemonType2.visibility = View.VISIBLE

        }else{
            pokemonType2.visibility = View.GONE
        }
        motionLayout.setBackgroundColor(Color.parseColor(pokemon.color))
    }



}

