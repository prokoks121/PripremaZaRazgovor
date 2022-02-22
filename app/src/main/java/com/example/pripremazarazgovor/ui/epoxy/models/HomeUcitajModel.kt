package com.example.pripremazarazgovor.ui.epoxy.models

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.pripremazarazgovor.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.home_button)
abstract class HomeUcitajModel:EpoxyModelWithHolder<HomeUcitajModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var listener: View.OnClickListener


    override fun bind(holder: ViewHolder) {
        holder.button.setOnClickListener(listener)
    }





    inner class ViewHolder:EpoxyHolder(){
        lateinit var button:Button
        override fun bindView(itemView: View) {
            button = itemView.findViewById(R.id.homeButton)
        }

    }

}