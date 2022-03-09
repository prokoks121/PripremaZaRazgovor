package com.example.pripremazarazgovor.ui.epoxy.models

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.*
import com.airbnb.epoxy.preload.Preloadable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.pripremazarazgovor.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.pokemon_image_pager)
abstract class PokemonImgModel:EpoxyModelWithHolder<PokemonImgModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var pokemonId:String

    @Override
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.glide.load("https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/${pokemonId.padStart(3, '0')}.png").into(holder.imageView)

    }

    inner class ViewHolder: EpoxyHolder(){
        lateinit var imageView: ImageView
        lateinit var glide: RequestManager

        override fun bindView(itemView: View) {
            imageView = itemView.findViewById(R.id.pokemonImg)
            glide = Glide.with(itemView)

        }


    }
}