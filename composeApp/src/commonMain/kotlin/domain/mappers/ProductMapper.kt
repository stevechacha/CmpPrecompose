package domain.mappers

import data.response.ProductDTO
import domain.model.Product
import domain.model.ProductDetails

fun List<ProductDTO>.toDomain(): List<Product> = map {
    Product(
        title = it.title,
        image = it.image,
        id = it.id
    )
}

fun ProductDTO.toDomain(): ProductDetails{
    return ProductDetails(
        title = this.title,
        id = this.id,
        image = this.image,
        desc = this.description
    )
}