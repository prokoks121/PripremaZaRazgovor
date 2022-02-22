package com.example.pripremazarazgovor.models


object ColorsMap {

    fun getColor(type:String)= hashMapOf(
            Pair("normal", "#6FABBA"),
            Pair("fighting", "#D03849"),
            Pair("flying", "#CADBEC"),
            Pair("poison", "#641C84"),
            Pair("ground", "#BD976F"),
            Pair("rock", "#8C8172"),
            Pair("bug", "#3F3F46"),
            Pair("ghost", "#324450"),
            Pair("steel", "#BCBCBA"),
            Pair("fire", "#F8750C"),
            Pair("water", "#3C719C"),
            Pair("grass", "#5E9F11"),
            Pair("electric", "#FAE004"),
            Pair("psychic", "#0C98D0"),
            Pair("ice", "#E9F2FC"),
            Pair("dragon", "#C5574E"),
            Pair("dark", "#041723"),
            Pair("fairy", "#E4F5F0"),
            Pair("unknown", "#0D8393"),
            Pair("shadow", "#8C847C")
        )[type]


}