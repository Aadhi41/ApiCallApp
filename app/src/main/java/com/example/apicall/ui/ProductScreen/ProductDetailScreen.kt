package com.example.apicall.ui.ProductScreen

import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apicall.ui.Models.ModelItem
import java.text.NumberFormat

@Composable
fun ProductDetailScreen(product: ModelItem, navController: NavController) {
    val context = LocalContext.current
    val formattedPrice = NumberFormat.getCurrencyInstance(java.util.Locale("en", "IN"))
        .format(product.price)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Product Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        AsyncImage(
            model = product.images.firstOrNull() ,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 16.dp),

        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Title: ${product.title}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Description: ${product.description}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 12.dp),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Price: $formattedPrice",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Green,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Category: ${product.category.name}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Product Ratings (if available)

            }
        }

        var isClicked by remember { mutableStateOf(false) }

        // "Buy Now" Button with Animation
        Button(
            onClick = {
                isClicked = true
                // Handle purchase logic
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .graphicsLayer {
                    scaleX = if (isClicked) 0.9f else 1f
                    scaleY = if (isClicked) 0.9f else 1f
                }
                .animateContentSize(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Buy Now", fontSize = 18.sp, color = Color.White)
        }

        Button(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "${product.title} - $ ${product.price}\n${product.description}")
                }
                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Share", fontSize = 16.sp, color = Color.White)
        }
    }
}
