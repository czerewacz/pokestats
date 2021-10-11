package ai.akun.pokeanalyzer.di


import ai.akun.pokeanalyzer.domain.IPokemonRepository
import ai.akun.pokeanalyzer.domain.usecases.GetPokemonUrlsUseCase
import ai.akun.pokeanalyzer.data.PokemonRepository
import ai.akun.pokeanalyzer.domain.usecases.GetPokeAveragesByTypeUseCase
import ai.akun.pokeanalyzer.domain.usecases.GetPokeAveragesUseCase
import ai.akun.pokeanalyzer.domain.usecases.GetPokemonStatsUseCase
import ai.akun.pokeanalyzer.presentation.PokeAnalyzerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeAnalyzerModule = module {
    single<IPokemonRepository> { PokemonRepository(get()) }

    factory { GetPokemonUrlsUseCase(get(), get()) }
    factory { GetPokemonStatsUseCase(get(), get()) }
    factory { GetPokeAveragesUseCase(get(), get(), get()) }
    factory { GetPokeAveragesByTypeUseCase(get(), get(), get()) }

    viewModel { PokeAnalyzerViewModel(get(), get()) }

}