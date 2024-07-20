package data.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val ratingDTO: RatingDTO,
    val title: String
)

@Serializable
data class RatingDTO(
    val counter: Int,
    val rate: Double
)
