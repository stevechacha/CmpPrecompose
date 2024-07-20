package data.repo

import data.response.ProductDTO
import domain.mappers.toDomain
import domain.model.Product
import domain.model.ProductDetails
import domain.repo.MainRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.chachadev.cmpprecompose.AppDatabase

class MainRepositoryImpl( private val httpClient: HttpClient,private val appDatabase: AppDatabase) : MainRepository{
    override suspend fun getProducts(): List<Product> {
        return httpClient.get("https://fakestoreapi.com/products").body<List<ProductDTO>>().toDomain()
    }

    override suspend fun getProductDetail(id: Int): ProductDetails {
        return httpClient.get("https//fakestoreapi.com/products/${id}").body<ProductDTO>().toDomain()
    }

    override suspend fun insert(id: Int, title: String, desc: String, image: String) {
        appDatabase.appDatabaseQueries.insert(
            id = id.toLong(),
            title = title,
            desc = desc,
            image = image
        )
    }

    override suspend fun delete(id: Int) {
        appDatabase.appDatabaseQueries.delete(id.toLong())
    }

    override suspend fun getProductList(): List<ProductDetails> {
       return appDatabase.appDatabaseQueries.getProductDetails().executeAsList().map {
           ProductDetails(
                id = it.id.toInt(),
                image = it.image.toString(),
                title = it.title.toString(),
                desc = it.desc.toString()
            )
        }
    }
}