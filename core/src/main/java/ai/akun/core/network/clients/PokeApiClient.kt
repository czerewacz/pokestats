package ai.akun.core.network.clients

import ai.akun.core.network.error.NetworkFailure
import ai.akun.core.network.request.PokemonStatsData
import ai.akun.core.network.request.PokemonUrlsData
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext


class PokeApiClient(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val getPokemonUrlsEndpoint: String,
    private val backgroundDispatcher: CoroutineContext
) {

    suspend fun getPokemonUrls(
        limit: Int,
        offset: Int
    ): PokemonUrlsData {
        return httpClient.get(
            urlString = "Https://pokeapi.co/api/v2/pokemon"
        ) {
            parameter("limit", limit)
            parameter("offset", offset)
        }
    }

    suspend fun getPokemon(
        pokemon: String
    ): PokemonStatsData {
        return httpClient.get(
            urlString = pokemon
        ) {
        }
    }

}