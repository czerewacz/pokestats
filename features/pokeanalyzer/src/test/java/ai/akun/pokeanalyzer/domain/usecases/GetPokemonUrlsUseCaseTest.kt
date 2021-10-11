package ai.akun.pokeanalyzer.domain.usecases

import ai.akun.pokeanalyzer.domain.IPokemonRepository
import ai.akun.pokeanalyzer.domain.model.PokemonUrl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.flow.single
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.Exception
import kotlin.test.assertIs

class GetPokemonUrlsUseCaseTest : KoinTest {

    private val getPokemonUrl: GetPokemonUrlsUseCase by inject()
    private val pokemonRepository: IPokemonRepository by inject()


    private val limit = 5
    private val offset = 10
    private val pokemonUrls = listOf<PokemonUrl>()


    @ExperimentalCoroutinesApi
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single { GetPokemonUrlsUseCase(get(), TestCoroutineDispatcher()) }
                single<IPokemonRepository> { mockk(relaxed = true) }
            })
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return success`() = runBlockingTest {
        coEvery {
            pokemonRepository.getPokemonUrls(
                limit = limit,
                offset = offset
            )
        } returns pokemonUrls
        assertIs<GetPokemonUrlsUseCase.Result.Success>(
            getPokemonUrl(
                limit = limit,
                offset = offset
            ).single()
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return error`() = runBlockingTest {
        coEvery {
            pokemonRepository.getPokemonUrls(
                limit = limit,
                offset = offset
            )
        }.throws(Exception())
        assertIs<GetPokemonUrlsUseCase.Result.Failure>(
            getPokemonUrl(
                limit = limit,
                offset = offset
            ).single()
        )
    }
}