package ai.akun.core.network.request

import kotlinx.serialization.Serializable

/**
 * Serializable for handling error responses
 */
@Serializable
class ErrorResponse(
    val code: String?= "400",
    val message: String = ""
)