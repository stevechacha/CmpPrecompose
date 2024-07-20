package presentation.productList

import domain.model.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val data: List<Product>? = null,
    val error: String = ""
)
