package com.example.apicall.ui.Navigation

import ProductListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apicall.ui.Models.ModelItem
import com.example.apicall.ui.ProductScreen.ProductDetailScreen


import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val gson = Gson()

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(navController = navController)
        }
        composable(
            route = "productDetail/{productJson}",
            arguments = listOf(navArgument("productJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val productJson = backStackEntry.arguments?.getString("productJson") ?: ""
            val product = gson.fromJson(productJson, ModelItem::class.java)
            ProductDetailScreen(product = product, navController = navController)
        }
    }
















    
}


