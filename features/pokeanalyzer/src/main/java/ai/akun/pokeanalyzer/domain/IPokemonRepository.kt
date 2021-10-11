package ai.akun.pokeanalyzer.domain

import ai.akun.pokeanalyzer.domain.model.Pokemon
import ai.akun.pokeanalyzer.domain.model.PokemonUrl


interface IPokemonRepository {

    suspend fun getPokemonUrls(limit: Int, offset: Int): List<PokemonUrl>

    suspend fun getPokemon(pokemon: String): Pokemon

}