package presentation.productList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.model.Product
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ProductListComponent(
    modifier: Modifier = Modifier,
    product: Product,
    onClick:(Int)-> Unit
) {
    Column {
        KamelImage(
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            resource = asyncPainterResource(product.image),
            contentDescription = null
        )
        Text(text = product.title)
        Row {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
            Button(onClick = { onClick(product.id) }){
                Text("Add to cart")
            }
        }


    }
}