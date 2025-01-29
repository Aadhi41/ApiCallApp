package com.example.apicall.ui.ProductScreen.detailscreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apicall.ui.Models.ModelItem

@Composable
fun ProductDetailScreen(product: ModelItem, navController: NavController) {
    val context = LocalContext.current
    var expandedDescription by remember { mutableStateOf(false) }
    var isClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ProductDetailHeader(navController)
        ProductImage(product)
        ProductInfoCard(product, expandedDescription) { expandedDescription = it }
        ProductActions(product, context, isClicked) { isClicked = it }
    }
}

