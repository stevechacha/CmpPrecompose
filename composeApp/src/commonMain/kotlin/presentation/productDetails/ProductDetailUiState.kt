package presentation.productDetails

import domain.model.ProductDetails

data class ProductDetailUiState(
    val data: ProductDetails?= null,
    val isLoading: Boolean = false,
    val error: String = ""
)
