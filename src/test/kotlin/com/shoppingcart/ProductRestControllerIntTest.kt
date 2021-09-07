package com.shoppingcart

import com.model.Product
import com.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRestControllerIntTest
@Autowired constructor(
    private val productRepository: ProductRepository,
    private val restTemplate: TestRestTemplate
) {
    private val productId = UUID.randomUUID()

    @LocalServerPort
    protected var port: Int = 0

    @BeforeEach
    fun setUp() {
        productRepository.deleteAll()
    }


    private fun getRootUrl(): String? = "http://localhost:$port/products"

    private fun saveOneProduct() = productRepository.save(Product(UUID.randomUUID(), "id"))

    @Test
    fun `should return all products`() {
        saveOneProduct()

        val response = restTemplate.getForEntity(
            getRootUrl(),
            List::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `should return single product id`() {
        saveOneProduct()

        val response = restTemplate.getForEntity(
            getRootUrl() + "/$productId",
            Product::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(productId, response.body?.id)
    }
}