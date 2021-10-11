package ai.akun.pokeanalyzer.domain.usecases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import ai.akun.pokeanalyzer.domain.IPokemonRepository
import ai.akun.pokeanalyzer.domain.model.PokemonUrl
import kotlin.coroutines.CoroutineContext

class GetPokemonUrlsUseCase(
    private val pokemonRepository: IPokemonRepository,
    private val backgroundDispatcher: CoroutineContext,
) {
    suspend operator fun invoke(
        limit: Int,
        offset: Int
    ): Flow<Result> {
        return flow<Result> {
            val pokeList = pokemonRepository.getPokemonUrls(
                limit = limit,
                offset = offset
            )
            emit(Result.Success(pokeList))
        }.flowOn(backgroundDispatcher).retry(retries = 3)
        {
            val seconds = (1000).toLong()
            delay(seconds)
            return@retry true
        }.catch { error ->
            emit(Result.Failure(error))
        }
    }

    sealed class Result {
        class Success(val pokemonUrls: List<PokemonUrl>) : Result()
        class Failure(val error: Throwable) : Result()
    }
}