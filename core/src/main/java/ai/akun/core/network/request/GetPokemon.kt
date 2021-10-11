package ai.akun.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonStatsData(
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("types") val types: List<Types>
)

@Serializable
data class Types(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val type: Type
)

@Serializable
data class Type(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

