package presentation.productDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import domain.model.ProductDetails
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailsViewModel,
    navigator: Navigator,
    onClick:(ProductDetails)->Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {}
    ){ paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
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

                else -> {
                     uiState.data?.let { productDetails->
                         ProductDetailsComponent(
                             productDetails = productDetails,
                             onClick = { onClick(productDetails) }
                         )
                     }
                }
            }
        }
    }

}