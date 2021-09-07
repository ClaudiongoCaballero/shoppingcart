package com.repository

import com.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartItemRepository : JpaRepository<CartItem, UUID> {
    fun findByShoppingCartUUID(uuid: UUID): List<CartItem>
}