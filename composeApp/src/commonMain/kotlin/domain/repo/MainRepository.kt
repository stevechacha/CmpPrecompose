package domain.repo

import domain.model.Product
import domain.model.ProductDetails

interface MainRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductDetail(id: Int): ProductDetails

    suspend fun insert(id: Int,title: String,desc: String,image: String)

    suspend fun delete(id: Int)

    suspend fun getProductList(): List<ProductDetails>
}