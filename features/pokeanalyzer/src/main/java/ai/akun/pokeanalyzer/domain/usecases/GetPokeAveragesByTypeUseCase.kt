package ai.akun.pokeanalyzer.domain.usecases

import ai.akun.pokeanalyzer.data.toPokeAverageByType
import ai.akun.pokeanalyzer.domain.model.PokemonAverage
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class GetPokeAveragesByTypeUseCase(
    private val getPokemonUrlsUseCase: GetPokemonUrlsUseCase,
    private val getPokemonStatsUseCase: GetPokemonStatsUseCase,
    private val backgroundDispatcher: CoroutineContext
) {
    suspend operator fun invoke(
        limit: Int,
        offset: Int
    ): Flow<Result> {
        return getPokemonUrlsUseCase(
            limit = limit,
            offset = offset
        ).flatMapConcat { result ->
            when (result) {
                is GetPokemonUrlsUseCase.Result.Success -> {
                    getPokemonStatsUseCase(result.pokemonUrls)
                }
                is GetPokemonUrlsUseCase.Result.Failure -> {
                    throw Throwable(result.error)
                }
            }
        }.map { result ->
            when (result) {
                is GetPokemonStatsUseCase.Result.Success -> {
                    Result.Success(result.pokemons.toPokeAverageByType())
                }
                is GetPokemonStatsUseCase.Result.Failure -> {
                    Result.Failure(result.error.message)
                }
            }
        }.flowOn(
            backgroundDispatcher
        ).catch { error ->
            if (error.message != null) {
                emit(Result.Failure(error.message!!))
            } else {
                emit(Result.Failure("Error to calculate Pokemons Average"))
            }
        }
    }

    sealed class Result {
        class Success(val pokemonAverage: List<PokemonAverage>) : Result()
        class Failure(val message: String?) : Result()
    }
}