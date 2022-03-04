package com.example.pripremazarazgovor.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.ui.epoxy.controlers.PokemonController
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
    private lateinit var pokemonImg:ImageView
    private lateinit var motionLayout: MotionLayout
    private lateinit var epoxyController:PokemonController
    private lateinit var recyclerView: ViewPager2
    private lateinit var tabLayout:TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initRecycleView(view)

        viewModel.pokemonId = navArgs.pokemonId
        viewModel.sendRequests()

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            setDataToView(pokemon,view)
            viewModel.pokemonSpecies.value?.let { pokemonSpecies ->
                epoxyController.setValues(pokemon = pokemon, pokemonSpecies = pokemonSpecies)
                setTabColor(pokemon.color)
            }
        }
        viewModel.pokemonSpecies.observe(viewLifecycleOwner) { pokemonSpecies ->
            viewModel.pokemon.value?.let { pokemon ->
                epoxyController.setValues(pokemon = pokemon, pokemonSpecies = pokemonSpecies)
            }
        }

    }

    private fun initViews(view: View){
        pokemonId = view.findViewById(R.id.b)
        pokemonImg = view.findViewById(R.id.pokemonImg)
        pokemonName = view.findViewById(R.id.pokemonName)
        pokemonType = view.findViewById(R.id.pokemonType)
        pokemonType2 = view.findViewById(R.id.pokemonType2)
        motionLayout = view.findViewById(R.id.pokemonMotion)
    }

    private fun initRecycleView(view:View){
        epoxyController = PokemonController()
        recyclerView = view.findViewById(R.id.epoxy)
        tabLayout = view.findViewById(R.id.tabL)
        recyclerView.adapter = epoxyController.adapter
        recyclerView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
           @Override
            override fun onPageSelected(position: Int) {
                    epoxyController.progressBarStartAnimation = position == 1
                }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                tabLayout.setScrollPosition(position, positionOffset, true)
                if (positionOffset == 0f){
                    tabLayout.selectTab(tabLayout.getTabAt(position),false)
                }
            }
        })
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { recyclerView.currentItem = it.position
                    epoxyController.progressBarStartAnimation = it.position == 1

                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
    private fun setTabColor(color:String){
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor(color))
    }


    @SuppressLint("SetTextI18n")
    private fun setDataToView(pokemon:Pokemon,view: View){
        pokemonId.text = "#${pokemon.id.toString().padStart(3, '0')}"
        pokemonType.text = pokemon.types[0].type.name
        if (pokemon.types.size>1){
            pokemonType2.text = pokemon.types[1].type.name
            pokemonType2.visibility = View.VISIBLE

        }else{
            pokemonType2.visibility = View.GONE
        }
        Glide.with(view).load("https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/${pokemon.id.toString().padStart(3, '0')}.png").into(pokemonImg)
        motionLayout.setBackgroundColor(Color.parseColor(pokemon.color))
    }
}