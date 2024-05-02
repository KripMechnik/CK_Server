package CK_Server.ru.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(val login: String, val number: String, val password: String)

@Serializable
data class RegisterResponseRemote(val token: String, val login: String, val number: String)