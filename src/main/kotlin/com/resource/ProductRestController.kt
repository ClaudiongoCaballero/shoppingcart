package com.resource

import com.model.Product
import com.request.ProductRequest
import com.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductRestController @Autowired constructor(
    private val productService: ProductService) {

    @GetMapping("/findAll")
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val products = productService.getAllProducts()
    return ResponseEntity.ok(products)
    }

    @PostMapping("/createProduct")
    fun createProduct(@RequestBody request: ProductRequest): ResponseEntity<Product> {
        val product = productService.createProduct(
            Product(
                id = request.id,
                name = request.name,
                description = request.description,
                sku = request.sku,
                price = request.price,
                product_type = request.product_type,
            )
        )
        return ResponseEntity(product, HttpStatus.CREATED)
    }

    @PutMapping("/updateProduct")
    fun updateProductById(@RequestBody request: ProductRequest): ResponseEntity<Product>{
        productService.updateProductById(request)
    return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/deleteProduct")
    fun deleteProductById(@RequestParam("id") id: UUID): ResponseEntity<String>{
        productService.deleteProductById(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
