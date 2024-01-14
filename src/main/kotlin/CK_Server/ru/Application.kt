package CK_Server.ru

import CK_Server.ru.features.login.configureLoginRouting
import CK_Server.ru.features.register.configureRegisterRouting
import CK_Server.ru.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect("jdbc:postgresql://localhost:5432/CK_Project", driver = "org.postgresql.Driver", password = "25042004")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
