package com.controller

import com.model.CartItem
import com.repository.CartItemRepository
import com.repository.ShoppingCartRepository

class ShoppingCartController(private val shoppingCartRepository: ShoppingCartRepository,
                             private val cartItemRepository: CartItemRepository) {

}

