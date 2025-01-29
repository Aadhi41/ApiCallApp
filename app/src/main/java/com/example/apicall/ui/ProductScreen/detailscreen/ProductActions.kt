package com.example.apicall.ui.ProductScreen.detailscreen

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apicall.ui.Models.ModelItem

@Composable
fun ProductActions(
    product: ModelItem,
    context: Context,
    isClicked: Boolean,
    onClickChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { onClickChange(true) },
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
                    putExtra(Intent.EXTRA_TEXT, "${product.title} - ₹${product.price}\n${product.description}")
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
