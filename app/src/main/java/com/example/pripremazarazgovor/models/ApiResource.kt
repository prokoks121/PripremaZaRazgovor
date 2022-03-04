package com.example.pripremazarazgovor.models

data class ApiResource(
    val url: String
)
data class NamedApiResource(
    val url: String,
    val name:String
)
data class ApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiResource>
)

data class NamedApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)

data class Pokemon(
    val id: Int,
    val name:String,
    val height:Int,
    val weight: Int,
    val stats:List<Stat>,
    val types:List<PokemonType>,
    val abilities:List<PokemonAbility>
):PokemonColor{
    override val color: String
        get() = ColorsMap.getColor(types[0].type.name) ?: "#FFFFFF"
}

interface PokemonColor{
    val color:String
}

data class Stat(
    val base_stat:Int,
    val stat:NamedApiResource
)
data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class PokemonAbility(
    val ability:NamedApiResource
)

data class PokemonSpecies(
    val id: Int,
    val name:String,
    val gender_rate:Int,
    val egg_groups:List<NamedApiResource>,
    val evolution_chain:ApiResource,
    val evolves_from_species:NamedApiResource?,
    val growth_rate:NamedApiResource
)