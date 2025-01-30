package com.example.apicall.ui.Models

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val category: Category,
    val creationAt: String,
    val updatedAt: String
)
