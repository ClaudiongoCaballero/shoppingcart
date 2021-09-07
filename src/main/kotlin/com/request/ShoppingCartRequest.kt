package com.request

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ShoppingCartRequest (
    var id: UUID = UUID.randomUUID()
)