package com.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "product")
data class Product(
    @Id val id: UUID = UUID.randomUUID(),
    var name: String? = null,
    var sku: String? = null,
    var description: String? = null,
    var price: String? = null,
    var product_type: String? = null)

