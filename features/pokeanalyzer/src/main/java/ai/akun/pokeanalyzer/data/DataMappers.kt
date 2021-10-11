package ai.akun.pokeanalyzer.data

import ai.akun.core.network.request.ApiPokemonUrl
import ai.akun.core.network.request.PokemonStatsData
import ai.akun.core.network.request.PokemonUrlsData
import ai.akun.pokeanalyzer.domain.model.Pokemon
import ai.akun.pokeanalyzer.domain.model.PokemonUrl

fun PokemonUrlsData.toPokemonUrlList(): List<PokemonUrl> {
    val list = this.pokemonUrlList.map {
        it.toPokemonUrl()
    }
    return list
}

fun ApiPokemonUrl.toPokemonUrl() = PokemonUrl(
    name = name,
    url = url
)

fun PokemonStatsData.toPokemon() = Pokemon(
    height = height,
    weight = weight,
    type = types.first().type.name
)