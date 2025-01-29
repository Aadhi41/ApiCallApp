package com.example.apicall.ui.ProductScreen

import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
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
    var expandedDescription by remember { mutableStateOf(false) }

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
                color = Color(0xFF78B3CE)
            )
        }

        AsyncImage(
            model = product.images.firstOrNull(),
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, Color.White, RoundedCornerShape(12.dp))
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F8EF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = product.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF78B3CE),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = if (expandedDescription) product.description else product.description.take(100) ,
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(bottom = 12.dp),
                    maxLines = if (expandedDescription) Int.MAX_VALUE else 5,
                    overflow = TextOverflow.Ellipsis
                )

                if (!expandedDescription) {
                    Text(
                        text = "Read More",
                        fontSize = 14.sp,
                        color = Color(0xFF0F79AF),
                        modifier = Modifier
                            .clickable { expandedDescription = true }
                            .padding(bottom = 8.dp)
                    )
                }

                Text(
                    text = formattedPrice,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFFD700),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = product.category.name,
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }

        var isClicked by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    isClicked = true
                },
                modifier = Modifier
                    .weight(1f)
                    .graphicsLayer {
                        scaleX = if (isClicked) 0.9f else 1f
                        scaleY = if (isClicked) 0.9f else 1f
                    }
                    .animateContentSize(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F79AF)) // Amazon Prime Blue
            ) {
                Text(text = "Buy Now", fontSize = 18.sp, color = Color.White)
            }

            Button(
                onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "${product.title} - $${product.price}\n${product.description}")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700))
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Share", fontSize = 16.sp, color = Color.Black)
            }
        }
    }
}
