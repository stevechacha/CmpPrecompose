package di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import presentation.cart.CartViewModel
import presentation.productList.ProductListViewModel
import presentation.productDetails.ProductDetailsViewModel

val presentationModule = module {
    factoryOf(::ProductListViewModel)
    factoryOf(::ProductDetailsViewModel)
    factoryOf(::CartViewModel)

}