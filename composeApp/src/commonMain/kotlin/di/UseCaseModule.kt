package di

import domain.useCases.DeleteProductDetailsUseCase
import domain.useCases.GetAllProductDetailsUseCase
import domain.useCases.GetProductDetailsUseCase
import domain.useCases.GetProductListUseCase
import domain.useCases.InsertProductDetailsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductListUseCase() }
    factory { GetProductDetailsUseCase() }
    factory { GetAllProductDetailsUseCase() }
    factory { InsertProductDetailsUseCase() }
    factory { DeleteProductDetailsUseCase() }
}