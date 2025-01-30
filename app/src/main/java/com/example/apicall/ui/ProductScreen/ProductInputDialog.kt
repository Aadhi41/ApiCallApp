package com.example.apicall.ui.ProductScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apicall.ui.Models.ProductRequest

@Composable
fun ProductInputDialog(
    onDismiss: () -> Unit,
    onSubmit: (ProductRequest) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Enter Product Details",
                color = Color(0xFF78B3CE),
                style = MaterialTheme.typography.h6
            )
        },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF9F8EF),
                        focusedIndicatorColor = Color(0xFF78B3CE),
                        unfocusedIndicatorColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF9F8EF),
                        focusedIndicatorColor = Color(0xFF78B3CE),
                        unfocusedIndicatorColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF9F8EF),
                        focusedIndicatorColor = Color(0xFF78B3CE),
                        unfocusedIndicatorColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = categoryId,
                    onValueChange = { categoryId = it },
                    label = { Text("Category ID") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF9F8EF),
                        focusedIndicatorColor = Color(0xFF78B3CE),
                        unfocusedIndicatorColor = Color.Gray
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val product = ProductRequest(
                        title = title,
                        price = price.toIntOrNull() ?: 0,
                        description = description,
                        categoryId = categoryId.toIntOrNull() ?: 1,
                        images = listOf("https://dfstudio-d420.kxcdn.com/wordpress/wp-content/uploads/2019/06/digital_camera_photo-980x653.jpg")
                    )
                    onSubmit(product)
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF96E2A)) // Orange color for the button
            ) {
                Text("Submit", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
            ) {
                Text("Cancel", color = Color.White)
            }
        },
        backgroundColor = Color(0xFFF9F8EF),
        shape = RoundedCornerShape(16.dp)
    )
}
