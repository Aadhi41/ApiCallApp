package com.example.apicall.ui.ProductScreen.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apicall.ui.Models.ModelItem
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductInfoCard(
    product: ModelItem,
    expandedDescription: Boolean,
    onExpandChange: (Boolean) -> Unit
) {
    val formattedPrice = NumberFormat.getCurrencyInstance(Locale.US).format(product.price)


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
                text = if (expandedDescription) product.description else product.description.take(100),
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
                        .clickable { onExpandChange(true) }
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
}
