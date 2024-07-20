package presentation.cart

import data.util.NetworkResult
import domain.useCases.DeleteProductDetailsUseCase
import domain.useCases.GetAllProductDetailsUseCase
import domain.useCases.InsertProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class CartViewModel(
    private val getAllProductDetailsUseCase: GetAllProductDetailsUseCase,
    private val deleteProductDetailsUseCase: DeleteProductDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    fun getAllProducts() = getAllProductDetailsUseCase().onEach { result ->
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

    fun delete(id: Int) = deleteProductDetailsUseCase(id)
        .onEach { result->
            _uiState.update { it.copy(data = result, isLoading = false) }
        }.launchIn(viewModelScope)
}