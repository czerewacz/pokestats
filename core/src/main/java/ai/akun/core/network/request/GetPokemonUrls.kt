package ai.akun.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonUrlsData(
    @SerialName("count") val count : Int,
    @SerialName("next") val next : String,
    @SerialName("previous") val previous : String,
    @SerialName("results") val pokemonUrlList: List<ApiPokemonUrl>,
)

@Serializable
data class ApiPokemonUrl(
    val name: String,
    val url: String
)