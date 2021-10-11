package ai.akun.pokeanalyzer.data

import ai.akun.pokeanalyzer.domain.model.Pokemon
import ai.akun.pokeanalyzer.domain.model.PokemonAverage

fun List<Pokemon>.toPokeAverage(): PokemonAverage {
    return PokemonAverage(
        averageHeight = this.map { it.height }.sum(),
        averageWeight = this.map { it.weight }.sum(),
        total = this.count(),
    )
}

fun List<Pokemon>.toPokeAverage(type: String): PokemonAverage {
    return PokemonAverage(
        averageHeight = this.map { it.height }.sum(),
        averageWeight = this.map { it.weight }.sum(),
        total = this.count(),
        type = type
    )
}

fun List<Pokemon>.toPokeAverageByType(): List<PokemonAverage> {

    val list: List<PokemonAverage> = this.groupBy { it.type }.map {
        it.value.toPokeAverage(it.key)
    }.toList()
    return list
}

//Hectograms to Kilograms
fun Int.toKG(): String {
    return "${this / 10} Kg"
}

fun Int.toMts(): String {
    return "${this / 10} m"
}