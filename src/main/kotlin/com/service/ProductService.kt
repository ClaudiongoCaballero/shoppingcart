package com.service

import com.exceptions.ProductNotFoundException
import com.model.Product
import com.repository.ProductRepository
import com.request.ProductRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService @Autowired constructor(private val productRepository: ProductRepository) {

    fun getAllProducts(): List<Product> = productRepository.findAll()

    fun getProductById(id: UUID): Product = productRepository.findById(id)
        .orElseThrow { ProductNotFoundException(HttpStatus.NOT_FOUND, "No matching Product was found") }

    fun createProduct(Product: Product): Product = productRepository.save(Product)

    fun updateProductById(productRequest: ProductRequest): Product {
        return if (productRepository.existsById(productRequest.id)) {
            productRepository.save(Product(
                id = productRequest.id,
                name = productRequest.name,
                description = productRequest.description,
                sku = productRequest.sku,
                price = productRequest.price,
                product_type = productRequest.product_type
            ))
        } else throw ProductNotFoundException(HttpStatus.NOT_FOUND, "No matching Product was found")
    }

    fun deleteProductById(id: UUID) {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
        } else throw ProductNotFoundException(HttpStatus.NOT_FOUND, "No matching Product was found")
    }
}