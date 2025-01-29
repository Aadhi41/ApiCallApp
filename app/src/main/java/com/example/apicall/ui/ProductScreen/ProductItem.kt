package com.example.apicall.ui.ProductScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apicall.ui.Models.ModelItem
import com.example.apicall.ui.ShimmerItem

@Composable
fun ProductItem(
    product: ModelItem,
    navController: NavController,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    if (isLoading) {
        ShimmerItem()
    } else {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .size(width = 180.dp, height = 300.dp) // Fixed card size
                .clickable(onClick = onClick),
            shape = RectangleShape, // Square edges for Netflix-like design
            //elevation = 4.dp // Subtle shadow for depth
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black) // Set card background to black
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    AsyncImage(
                        model = product.images.firstOrNull() ?: "", // Fallback to an empty string if null
                        contentDescription = product.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                shape = RectangleShape // Square edges for images
                                clip = true
                            }
                            .border(BorderStroke(2.dp, Color.White)), // White border for polaroid effect
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$ ${product.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}


