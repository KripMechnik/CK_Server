package CK_Server.ru.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(val login: String, val password: String)

@Serializable
data class LoginResponseRemote(val token: String, val login: String, val number: String)

