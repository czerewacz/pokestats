package ai.akun.pokeanalyzer.data

import ai.akun.core.network.clients.PokeApiClient
import ai.akun.pokeanalyzer.domain.model.Pokemon
import ai.akun.pokeanalyzer.domain.IPokemonRepository
import ai.akun.pokeanalyzer.domain.model.PokemonUrl

class PokemonRepository(
    private val apiClient: PokeApiClient
) : IPokemonRepository {

    override suspend fun getPokemonUrls(limit: Int, offset: Int): List<PokemonUrl> {
        return apiClient.getPokemonUrls(limit = limit, offset = offset).toPokemonUrlList()
    }

    override suspend fun getPokemon(pokemon: String): Pokemon {
        return apiClient.getPokemon(pokemon).toPokemon()
    }
}