package CK_Server.ru.database.products

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ProductsModel: Table("products") {
    val id = ProductsModel.varchar("id", 50)
    val name = ProductsModel.varchar("name", 25)
    val image = ProductsModel.varchar("image", 50)
    val price = ProductsModel.varchar("price", 15)

    fun getAllProducts(): List<ResultRow>{
        return transaction {
            val query = ProductsModel.selectAll()
            return@transaction query.toList()
        }
    }

    fun fetchProduct(id: String): ProductDTO{
        return transaction {
                val productModel = ProductsModel.select{ ProductsModel.id.eq(id)}.single()
                ProductDTO(
                    id = productModel[ProductsModel.id],
                    name = productModel[name],
                    image = productModel[image],
                    price = productModel[price]
                )
        }
    }
}

