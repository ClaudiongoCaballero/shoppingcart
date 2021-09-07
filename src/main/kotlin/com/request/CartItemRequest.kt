package com.request

import com.model.Product
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CartItemRequest (
    var product: Product,
    var quantity: Int=0,
    var shoppingCartUUID: UUID
)
