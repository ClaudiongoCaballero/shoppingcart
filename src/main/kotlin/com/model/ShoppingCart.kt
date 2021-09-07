package com.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "shopping_cart")
data class ShoppingCart (
    @Id
    val id: UUID = UUID.randomUUID(),
    var status: Int=0)
