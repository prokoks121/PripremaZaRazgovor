package com.example.pripremazarazgovor.ui.epoxy.models

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.airbnb.epoxy.*
import com.example.pripremazarazgovor.R
import com.example.pripremazarazgovor.models.Pokemon


@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.pokemon_stats)
abstract class PokemonStatsModel:EpoxyModelWithHolder<PokemonStatsModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var pokemon:Pokemon

    @EpoxyAttribute
    var progressBarAnimationStart = false



    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        holder.hp.text = pokemon.stats[0].base_stat.toString()
        holder.attack.text = pokemon.stats[1].base_stat.toString()
        holder.defense.text = pokemon.stats[2].base_stat.toString()
        holder.spAtk.text = pokemon.stats[3].base_stat.toString()
        holder.spDef.text = pokemon.stats[4].base_stat.toString()
        holder.speed.text = pokemon.stats[5].base_stat.toString()
        holder.total.text = "500"

        if (progressBarAnimationStart){
            holder.hpProgress.smoothProgress(pokemon.stats[0].base_stat)
            holder.attackProgress.smoothProgress(pokemon.stats[1].base_stat)
            holder.defenseProgress.smoothProgress(pokemon.stats[2].base_stat)
            holder.spAtkProgress.smoothProgress(pokemon.stats[3].base_stat)
            holder.spDefProgress.smoothProgress(pokemon.stats[4].base_stat)
            holder.speedProgress.smoothProgress(pokemon.stats[5].base_stat)
            holder.totalProgress.smoothProgress(500)
        }else{
            holder.hpProgress.smoothProgress(0)
            holder.attackProgress.smoothProgress(0)
            holder.defenseProgress.smoothProgress(0)
            holder.spAtkProgress.smoothProgress(0)
            holder.spDefProgress.smoothProgress(0)
            holder.speedProgress.smoothProgress(0)
            holder.totalProgress.smoothProgress(0)
        }
    }

    fun ProgressBar.smoothProgress(percent: Int){
        val animation = ObjectAnimator.ofInt(this, "progress", percent)
        animation.duration = 400
        animation.interpolator = FastOutSlowInInterpolator()
        animation.start()
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