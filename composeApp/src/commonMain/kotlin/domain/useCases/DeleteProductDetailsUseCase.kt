package domain.useCases

import data.util.NetworkResult
import domain.model.Product
import domain.model.ProductDetails
import domain.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteProductDetailsUseCase : KoinComponent {
    private val mainRepository: MainRepository by inject()

    operator fun invoke(id: Int) = flow<List<ProductDetails>> {
        mainRepository.delete(id)
        emit(mainRepository.getProductList())
    }.flowOn(Dispatchers.IO)
}