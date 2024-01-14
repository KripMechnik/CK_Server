package CK_Server.ru.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable


@Serializable
data class Test(val test1: String, val Test2: String)

fun Application.configureSerialization(){
    install(ContentNegotiation) {
        json()
    }

}