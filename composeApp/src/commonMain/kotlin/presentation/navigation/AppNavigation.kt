package presentation.navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import presentation.cart.CartScreen
import presentation.cart.CartViewModel
import presentation.productDetails.ProductDetailScreen
import presentation.productDetails.ProductDetailsViewModel
import presentation.productList.ProductListScreen
import presentation.productList.ProductListViewModel

@Composable
fun AppNavigation() {
    val navigator = rememberNavigator()

    NavHost(navigator = navigator, initialRoute = ""){
        scene(Screens.ProductList.route){
            val viewModel: ProductListViewModel = koinViewModel(ProductListViewModel::class)
            ProductListScreen(viewModel){
                navigator.navigate(Screens.ProductDetails.getRoute(it))
            }
        }

        scene(Screens.ProductDetails.route){
            val id = it.path.filter { it.isDigit() }
            val viewModel: ProductDetailsViewModel = koinViewModel(ProductDetailsViewModel::class)
            viewModel.getProductDetails(id.toInt())
            ProductDetailScreen(viewModel,navigator){ productDetail ->
                viewModel.insert(productDetail.id,productDetail.title,productDetail.image,productDetail.desc)
            }
        }

        scene(Screens.Cart.route) {
            val viewModel: CartViewModel = koinViewModel(CartViewModel::class)
            CartScreen(navigator,viewModel) {
                viewModel.delete(it)
                viewModel.getAllProducts()
            }
        }

    }
}

sealed class Screens(val route: String){
    data object ProductList: Screens("/product_list")
    data object ProductDetails: Screens("/product_details/{id}"){
        fun getRoute(id: Int) = "/product_details/${id}"
    }

    data object Cart: Screens("/cart")
}