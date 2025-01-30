
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apicall.ui.Apicalls.ProductViewModel
import com.example.apicall.ui.ProductScreen.ProductItem
import com.example.apicall.ui.ShimmerItem

@Composable
fun ProductListScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val isLoading by viewModel.isLoading.collectAsState()
    val products by viewModel.products.collectAsState()
    val gson = Gson()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            if (isLoading) {
                items(6) {
                    ShimmerItem()
                }
            } else {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        navController = navController,
                        isLoading = false
                    ) {
                        val productJson = Uri.encode(gson.toJson(product))
                        navController.navigate("productDetail/$productJson")
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { viewModel.postProduct() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.Blue
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Product")
        }
    }
}
