import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.apicall.ui.screens.AddProductScreen
import com.example.apicall.ui.screens.DetailScreen
import com.example.apicall.ui.screens.ProductScreen
import com.example.apicall.ui.viewmodel.ProductViewModel

@Composable
fun AppNavigation(viewModel: ProductViewModel = ProductViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductScreen(navController, viewModel)
        }
        composable(
            "detailScreen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            DetailScreen(productId, viewModel)
        }
        composable("addProduct") {
            AddProductScreen(navController, viewModel)
        }
    }
}
