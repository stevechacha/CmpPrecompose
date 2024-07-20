package presentation.productDetails

import data.util.NetworkResult
import domain.useCases.GetProductDetailsUseCase
import domain.useCases.InsertProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ProductDetailsViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val insertProductDetailsUseCase: InsertProductDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun getProductDetails(id: Int) = getProductDetailsUseCase(id).onEach { result ->
        when (result) {
            is NetworkResult.Error -> {
                _uiState.update { it.copy(error = result.message) }
            }

            is NetworkResult.Loading -> {
                _uiState.update { it.copy(isLoading = true) }
            }

            is NetworkResult.Success -> {
                _uiState.update { it.copy(data = result.data, isLoading = false) }
            }
        }
    }.launchIn(viewModelScope)


    fun insert(id: Int, title: String, image: String, desc: String) = insertProductDetailsUseCase(
        id = id,
        title = title,
        image = image,
        desc = desc
    ).launchIn(viewModelScope)
}