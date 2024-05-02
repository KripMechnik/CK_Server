package CK_Server.ru.features.register

import CK_Server.ru.database.tokens.TokenDTO
import CK_Server.ru.database.tokens.TokenModel
import CK_Server.ru.database.users.UserDTO
import CK_Server.ru.database.users.UserModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class RegisterController(val call: ApplicationCall) {

    suspend fun registerNewUser(){
        val receive = call.receive<RegisterReceiveRemote>()
        val userDTO = UserModel.fetchUser(receive.login)

        if(userDTO != null){
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()
            UserModel.insert(UserDTO(login = receive.login, password = receive.password, number = receive.number))

            TokenModel.insert(TokenDTO(id = UUID.randomUUID().toString(), login = receive.login, token = token))

            call.respond(RegisterResponseRemote(token = token, login = receive.login, number = receive.number))
        }
    }
}