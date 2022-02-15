package com.example.pripremazarazgovor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.models.HomeModel

class HomeAdapter(var listaPokemona:ArrayList<HomeModel>, val fragment: Fragment):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    sealed class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        class PokemonCardViewHolder(itemView: View):ViewHolder(itemView) {
            val name = itemView.findViewById<TextView>(R.id.PokemonName)
            val type = itemView.findViewById<TextView>(R.id.pokemonType)
            val img = itemView.findViewById<ImageView>(R.id.pokemonImg)
        }

         class ViewHolderHeader(itemView: View):ViewHolder(itemView){
            var text = itemView.findViewById<TextView>(R.id.headerText)
        }

        class ViewHolderButton(itemView: View):ViewHolder(itemView){
            var button = itemView.findViewById<Button>(R.id.homeButton)
        }


    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            R.layout.home_header-> ViewHolder.ViewHolderHeader(LayoutInflater.from(parent.context).inflate(R.layout.home_header,parent,false))
            R.layout.home_button ->  ViewHolder.ViewHolderButton(LayoutInflater.from(parent.context).inflate(R.layout.home_button,parent,false))
            else -> ViewHolder.PokemonCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false))
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder){
            is ViewHolder.ViewHolderHeader->{
                val pokemon = listaPokemona[position] as HomeModel.Header
                holder.text.text = pokemon.title
            }
            is ViewHolder.ViewHolderButton ->{
                val pokemon = listaPokemona[position] as HomeModel.Button
                holder.button.text = pokemon.title
            }
            else ->{
                holder as ViewHolder.PokemonCardViewHolder
                val pokemon = listaPokemona[position] as HomeModel.Pokemon

                Glide.with(fragment).load(pokemon.img).into(holder.img)
                holder.name.text = pokemon.name
                holder.type.text = pokemon.type
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
       return  when(listaPokemona[position]){
            is HomeModel.Header -> R.layout.home_header
            is HomeModel.Button -> R.layout.home_button
            else -> R.layout.pokemon_item


       }
    }

    override fun getItemCount(): Int {
       return listaPokemona.size
    }
}