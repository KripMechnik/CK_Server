package CK_Server.ru.features.products

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetProductsRouting() {
    routing {
        get("/get_products") {
            val productController = ProductController(call)
            productController.getProducts()
            return@get
        }
    }
}

fun Application.configureGetProductByIDRouting(){
    routing {
        get("/get_products/get_by_id"){
            val productController = ProductController(call)
            productController.getProductByID()
            return@get
        }
    }
}