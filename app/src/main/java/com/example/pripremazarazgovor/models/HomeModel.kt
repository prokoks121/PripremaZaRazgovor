package com.example.pripremazarazgovor.models

sealed class HomeModel{
    data class Pokemon(
        val name:String,
        val type:String,
        val img:String,

        ):HomeModel()
    data class Header(
        val title:String
    ):HomeModel()

    data class Button(
        val title:String
    ):HomeModel()

}
