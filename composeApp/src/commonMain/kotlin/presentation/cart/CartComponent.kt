package presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.model.ProductDetails
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CartComponent(
    modifier: Modifier = Modifier,
    productDetails: ProductDetails
) {
    Column {
        KamelImage(
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            resource = asyncPainterResource(productDetails.image),
            contentDescription = null
        )
        Text(text = productDetails.title)
    }
}