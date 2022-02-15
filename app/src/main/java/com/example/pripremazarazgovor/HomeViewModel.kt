package com.example.pripremazarazgovor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pripremazarazgovor.models.HomeModel

class HomeViewModel:ViewModel() {

    val listaPokemona by lazy {
        MutableLiveData<ArrayList<HomeModel.Pokemon>>()
    }


}