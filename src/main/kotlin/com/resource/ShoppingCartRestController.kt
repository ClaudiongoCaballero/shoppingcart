package com.resource

import com.enums.ShoppingCartStatus
import com.model.CartItem
import com.model.ShoppingCart
import com.repository.CartItemRepository
import com.repository.ShoppingCartRepository
import com.request.CartItemRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/shoppingCart")
class ShoppingCartRestController @Autowired constructor(
    private val shoppingCartRepository: ShoppingCartRepository,
    private val cartItemRepository: CartItemRepository
) {

    @GetMapping("/findAllCartItems")
    fun getAllCartItems(@RequestParam("id") uuid: UUID): ResponseEntity<List<CartItem>> {
        val cartItems = cartItemRepository.findByShoppingCartUUID(uuid)
        return ResponseEntity.ok(cartItems)
    }


    @PostMapping("/addCartItemToShoppingCart")
    fun addCartItemToShoppingCart(@RequestBody request: CartItemRequest): ResponseEntity<String> {
        val cartitem = cartItemRepository.save(CartItem(
            id = UUID.randomUUID(),
            product = request.product,
            quantity = request.quantity,
            shoppingCartUUID = request.shoppingCartUUID
        ))
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/createShoppingCart")
    fun createShoppingCart(): ResponseEntity<ShoppingCart> {
        val shoppingCart = shoppingCartRepository.save(ShoppingCart(
           id = UUID.randomUUID(),
           status = ShoppingCartStatus.PENDING.status
        ))
        return ResponseEntity(shoppingCart, HttpStatus.CREATED)
    }

    @DeleteMapping("/deleteCartItemOfShoppingCart")
    fun deleteCartItemOfShoppingCart(@RequestBody request: CartItemRequest): ResponseEntity<String> {
        val cartitem = cartItemRepository.delete(CartItem(
            product = request.product,
            quantity = request.quantity,
            shoppingCartUUID = request.shoppingCartUUID
        ))
        return ResponseEntity(HttpStatus.OK)
    }

    @PutMapping("/updateCartItemOfShoppingCart")
    fun updateCartItemOfShoppingCart(@RequestBody request: CartItemRequest): ResponseEntity<String> {
        val cartitem = cartItemRepository.delete(CartItem(
            product = request.product,
            quantity = request.quantity,
            shoppingCartUUID = request.shoppingCartUUID
        ))
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/complete")
    fun complete(@RequestParam("id") uuid: UUID): ResponseEntity<String> {
        val shoppingCart = shoppingCartRepository.findById(uuid)
            shoppingCartRepository.save(ShoppingCart(
               id = shoppingCart.get().id,
               status = ShoppingCartStatus.COMPLETED.status
            ))
        return ResponseEntity(HttpStatus.OK)
    }



}
