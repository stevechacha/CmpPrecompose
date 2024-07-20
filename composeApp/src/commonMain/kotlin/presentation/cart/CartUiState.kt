package presentation.cart

import domain.model.ProductDetails

data class CartUiState(
    val data: List<ProductDetails>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
