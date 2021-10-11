package ai.akun.pokeanalyzer.domain.model

data class Pokemon(
    val height: Int,
    val weight: Int,
    val type: String
)


data class PokemonAverage(
    val averageHeight: Int = 0,
    val averageWeight: Int = 0,
    val total: Int = 0,
    val type: String = ""
)
