package com.example.pripremazarazgovor.ui.epoxy.models

import android.annotation.SuppressLint
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.models.Pokemon
import com.example.pripremazarazgovor.models.PokemonSpecies

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.pokemon_stats)
abstract class PokemonStatsModel:EpoxyModelWithHolder<PokemonStatsModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var pokemon:Pokemon



    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        holder.hp.text = pokemon.stats[0].base_stat.toString()
        holder.attack.text = pokemon.stats[1].base_stat.toString()
        holder.defense.text = pokemon.stats[2].base_stat.toString()
        holder.spAtk.text = pokemon.stats[3].base_stat.toString()
        holder.spDef.text = pokemon.stats[4].base_stat.toString()
        holder.speed.text = pokemon.stats[5].base_stat.toString()
        holder.total.text = "500"

        holder.hpProgress.setProgress(pokemon.stats[0].base_stat,true)
        holder.attackProgress.setProgress(pokemon.stats[1].base_stat,true)
        holder.defenseProgress.setProgress(pokemon.stats[2].base_stat,true)
        holder.spAtkProgress.setProgress(pokemon.stats[3].base_stat,true)
        holder.spDefProgress.setProgress(pokemon.stats[4].base_stat,true)
        holder.speedProgress.setProgress(pokemon.stats[5].base_stat,true)
        holder.totalProgress.setProgress(500,true)


    }

    inner class ViewHolder:EpoxyHolder(){

        lateinit var hp:TextView
        lateinit var attack:TextView
        lateinit var defense:TextView
        lateinit var spAtk:TextView
        lateinit var spDef:TextView
        lateinit var speed:TextView
        lateinit var total:TextView


        lateinit var hpProgress:ProgressBar
        lateinit var attackProgress:ProgressBar
        lateinit var defenseProgress:ProgressBar
        lateinit var spAtkProgress:ProgressBar
        lateinit var spDefProgress:ProgressBar
        lateinit var speedProgress:ProgressBar
        lateinit var totalProgress:ProgressBar



        override fun bindView(itemView: View) {
            hp = itemView.findViewById(R.id.hp_data)
            attack = itemView.findViewById(R.id.attack_data)
            defense = itemView.findViewById(R.id.defense_data)
            spAtk = itemView.findViewById(R.id.atk_data)
            spDef = itemView.findViewById(R.id.def_data)
            speed = itemView.findViewById(R.id.speed_data)
            total = itemView.findViewById(R.id.total_data)
            hpProgress = itemView.findViewById(R.id.progress_hp)
            attackProgress = itemView.findViewById(R.id.progress_attack)
            defenseProgress = itemView.findViewById(R.id.progress_defense)
            spAtkProgress = itemView.findViewById(R.id.progress_atk)
            spDefProgress = itemView.findViewById(R.id.progress_def)
            speedProgress = itemView.findViewById(R.id.progress_speed)
            totalProgress = itemView.findViewById(R.id.progress_total)

        }

    }
}