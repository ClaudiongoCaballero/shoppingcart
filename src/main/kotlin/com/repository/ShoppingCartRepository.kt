package com.repository

import com.model.ShoppingCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShoppingCartRepository : JpaRepository<ShoppingCart, UUID> {

}