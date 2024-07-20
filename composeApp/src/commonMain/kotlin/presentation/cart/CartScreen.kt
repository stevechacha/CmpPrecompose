package presentation.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
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

@Composable
fun CartScreen(
    navigator: Navigator,
    viewModel: CartViewModel,
    onClick:(Int)-> Unit
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
                    if (uiState.data.isNullOrEmpty()){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Data is Empty")
                        }

                    } else {
                      LazyColumn {
                         items(uiState.data!!)  { productDetails->
                             Column {
                                 productDetails.let { value->
                                     KamelImage(
                                         modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                                         resource = asyncPainterResource(value.image),
                                         contentDescription = null
                                     )
                                     Text(text = value.title)
                                     Button(onClick = {
                                         onClick(value.id)
                                     }){
                                         Text("Delete")
                                     }
                                 }

                             }
                          }
                      }
                    }
                }
            }
        }
    }

}