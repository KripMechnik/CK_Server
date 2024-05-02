package CK_Server.ru.features.login

import CK_Server.ru.database.tokens.TokenDTO
import CK_Server.ru.database.tokens.TokenModel
import CK_Server.ru.database.users.UserModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(val call: ApplicationCall) {

    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = UserModel.fetchUser(receive.login)

        if(userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == receive.password){
                val token = UUID.randomUUID().toString()
                TokenModel.insert(TokenDTO(id = UUID.randomUUID().toString(), login = receive.login, token = token))

                call.respond(LoginResponseRemote(token = token, login = userDTO.login, number = userDTO.number))
            }
        }
    }
}