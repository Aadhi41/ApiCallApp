package com.example.apicall.domain

import com.example.apicall.data.apicalls.RetrofitInstance
import com.example.apicall.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository {
    private val api = RetrofitInstance.api

    fun getProducts(): Flow<List<Product>> = flow {
        try {
            val response = api.getProducts()
            emit(response.products)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
