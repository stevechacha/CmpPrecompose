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

class InsertProductDetailsUseCase : KoinComponent {
    private val mainRepository: MainRepository by inject()

    operator fun invoke(id: Int,title: String,image: String,desc: String) = flow<Unit> {
        mainRepository.insert(
            id = id,
            title = title,
            image = image,
            desc = desc
        )

    }.flowOn(Dispatchers.IO)
}