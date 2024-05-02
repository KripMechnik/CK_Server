package CK_Server.ru.features.products

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponseRemote(val id: String, val name: String, val image: String, val price: String)

@Serializable
data class ProductsReceiveRemote(val id: String)