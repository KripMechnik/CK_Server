package CK_Server.ru.features.products

import CK_Server.ru.database.products.ProductsModel
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ProductController(private val call: ApplicationCall) {

    suspend fun getProducts(){
        val products = ProductsModel.getAllProducts()
        val productsList = mutableListOf<ProductsResponseRemote>()

        for (product in products){
            val id = product[ProductsModel.id]
            val name = product[ProductsModel.name]
            val price = product[ProductsModel.price]
            val image = product[ProductsModel.image]

            val productResp = ProductsResponseRemote(id, name, image, price)
            productsList.add(productResp)
        }

        call.respond(productsList)
    }

    suspend fun getProductByID(){
        val receive = call.receive<ProductsReceiveRemote>()
        val product = ProductsModel.fetchProduct(receive.id)
        call.respond(ProductsResponseRemote(product.id, product.name, product.image, product.price))
    }
}