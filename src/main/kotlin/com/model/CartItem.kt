package com.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cart_item")
data class CartItem (
    @Id val id: UUID = UUID.randomUUID(),
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    var product: Product,
    @Column(name = "quantity")
    var quantity: Int=0,
    @Column(name = "shopping_cart_id")
    var shoppingCartUUID: UUID )
