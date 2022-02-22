package com.example.pripremazarazgovor.ui.epoxy.models

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.pripremazarazgovor.R


@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.pokemon_item)
abstract class PokemonModel:EpoxyModelWithHolder<PokemonModel.ViewHolder>() {

@EpoxyAttribute
lateinit var name:String
@EpoxyAttribute
lateinit var type:String

@EpoxyAttribute
 var type2:String? = null

@EpoxyAttribute
lateinit var color:String

@EpoxyAttribute
lateinit var pokemonId:String

    override fun bind(holder: ViewHolder) {
        holder.name.text = name
        holder.type.text = type
        holder.glide.load("https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/${pokemonId.padStart(3, '0')}.png").into(holder.img)
        holder.drawable.setColor(Color.parseColor(color))
        if(type2 != null){
            holder.type2.text = type2
        }else{
            holder.type2.visibility = View.GONE
        }
    }




    inner class ViewHolder:EpoxyHolder(){
        lateinit var name:TextView
        lateinit var type:TextView
        lateinit var type2:TextView
        lateinit var img:ImageView
        lateinit var glide: RequestManager
        lateinit var drawable:GradientDrawable
        override fun bindView(itemView: View) {
            name = itemView.findViewById(R.id.PokemonName)
            type = itemView.findViewById(R.id.pokemonType)
            img = itemView.findViewById(R.id.pokemonImg)
            drawable = itemView.background as GradientDrawable
            glide = Glide.with(itemView)
            type2 = itemView.findViewById(R.id.pokemonType2)

        }

    }
}