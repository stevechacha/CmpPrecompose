package presentation.productList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.navigation.Navigator
import presentation.navigation.Screens

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    onClick: (Int)->Unit,
    navigator: Navigator
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            Text("Click", Modifier.clickable {  navigator.navigate(Screens.Cart.route)})
        }
    ){
        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }

            }

            uiState.error.isNotEmpty() ->{
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text( text = uiState.error.toString())
                }

            }

            else  -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ){
                    uiState.data?.let { productList ->
                        items(productList) {product->
                            ProductListComponent(
                                product = product,
                                onClick = { onClick(product.id)}
                            )
                        }
                    }
                }
            }
        }

    }

}