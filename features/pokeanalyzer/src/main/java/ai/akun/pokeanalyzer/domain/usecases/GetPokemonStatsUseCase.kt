package ai.akun.pokeanalyzer.domain.usecases

import ai.akun.pokeanalyzer.domain.model.Pokemon
import kotlinx.coroutines.flow.*
import ai.akun.pokeanalyzer.domain.IPokemonRepository
import ai.akun.pokeanalyzer.domain.model.PokemonUrl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class GetPokemonStatsUseCase(
    private val pokemonRepository: IPokemonRepository,
    private val backgroundDispatcher: CoroutineContext,
) {
    suspend operator fun invoke(
        pokeUrls: List<PokemonUrl>
    ): Flow<Result> {
        return flow<Result> {
            var pokemons = listOf<Pokemon>()

            //Split List in two
            val n = pokeUrls.size    // get the size of the list
            val first = pokeUrls.subList(0, (n + 1) / 2)
            val second = pokeUrls.subList((n + 1) / 2, n)

            with(CoroutineScope(currentCoroutineContext())) {
                val firstList = mutableListOf<Pokemon>()
                val secondList = mutableListOf<Pokemon>()

                val a = async {
                    first.forEach { pokemonUrl ->
                        pokemonRepository.getPokemon(
                            pokemonUrl.url
                        ).let { pokemon ->
                            firstList.add(pokemon)
                        }
                    }
                    firstList
                }
                val b = async {
                    second.forEach { pokemonUrl ->
                        pokemonRepository.getPokemon(
                            pokemonUrl.url
                        ).let { pokemon ->
                            secondList.add(pokemon)
                        }
                    }
                    secondList
                }

                val (resA, resB) = awaitAll(a, b)
                pokemons = ai.akun.core.extensions.merge(resA, resB)
                emit(Result.Success(pokemons))
            }
        }.flowOn(backgroundDispatcher).catch { error ->
            emit(Result.Failure(error))
        }
    }

    sealed class Result {
        class Success(val pokemons: List<Pokemon>) : Result()
        class Failure(val error: Throwable) : Result()
    }
}