package com.request

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ProductRequest (
    val id: UUID,
    val name: String,
    val description: String,
    val sku: String,
    val price: String,
    val product_type: String
)