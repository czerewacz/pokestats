package ai.akun.core.network.di

import ai.akun.core.network.Endpoints.BASE_URL
import ai.akun.core.network.Endpoints.GET_POKEMONS
import ai.akun.core.network.clients.ProdHttpClient
import ai.akun.core.network.clients.PokeApiClient
import org.koin.dsl.module

val networkModule = module {
    single {
        PokeApiClient(
            httpClient = ProdHttpClient().httpClient,
            baseUrl = BASE_URL,
            getPokemonUrlsEndpoint = GET_POKEMONS,
            backgroundDispatcher = get()
        )
    }
}