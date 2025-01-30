package com.example.apicall.ui.Models

data class ProductRequest(
    val title: String,
    val price: Int,
    val description: String,
    val categoryId: Int,
    val images: List<String>
)
