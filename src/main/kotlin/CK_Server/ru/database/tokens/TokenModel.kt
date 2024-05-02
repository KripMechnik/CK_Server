package CK_Server.ru.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object TokenModel: Table("tokens") {
    private val login = TokenModel.varchar("login", 25)
    private val id = TokenModel.varchar("id", 50)
    private val token = TokenModel.varchar("token", 75)

    fun insert(tokenDTO: TokenDTO){
        transaction {
            TokenModel.insert{
                it[id] = tokenDTO.id
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }
}