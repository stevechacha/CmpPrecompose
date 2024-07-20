package presentation.productDetails

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
import domain.model.ProductDetails
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ProductDetailsComponent(
    modifier: Modifier = Modifier,
    productDetails: ProductDetails,
    onClick: (ProductDetails)->Unit
) {
    Column {
        KamelImage(
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            resource = asyncPainterResource(productDetails.image),
            contentDescription = null
        )
        Text(text = productDetails.title)
        Row {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
            Button(onClick = { onClick(productDetails) }){
                Text("Add to cart")
            }
        }


    }
}