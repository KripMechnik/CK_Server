package CK_Server.ru.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel: Table("users") {
    private val login = UserModel.varchar("login", 25)
    private val password = UserModel.varchar("password", 25)
    private val number = UserModel.varchar("number", 25)

    fun insert(userDTO: UserDTO){
        transaction {
            UserModel.insert{
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[number] = userDTO.number
            }
        }
    }

    fun fetchUser(login: String): UserDTO?{
        return try {
            transaction {
                val userModel = UserModel.select{ UserModel.login.eq(login)}.single()
                UserDTO(
                    login = userModel[UserModel.login],
                    password = userModel[password],
                    number = userModel[number]
                )
            }
        } catch (e: Exception){
            null
        }

    }
}