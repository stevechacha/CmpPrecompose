package presentation.productList

import data.util.NetworkResult
import domain.useCases.GetProductListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        getProductList()
    }

    fun getProductList() = getProductListUseCase().onEach { result ->
        when(result){
            is NetworkResult.Error -> {
                _uiState.update { it.copy( error = result.message) }
            }
            is NetworkResult.Loading -> {
                _uiState.update { it.copy(isLoading = true) }
            }
            is NetworkResult.Success -> {
                _uiState.update { it.copy(data = result.data, isLoading = false) }
            }
        }
    }.launchIn(viewModelScope)


}