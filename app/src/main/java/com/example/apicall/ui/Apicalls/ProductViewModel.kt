package com.example.apicall.ui.Apicalls

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicall.ui.Models.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<ModelItem>>(emptyList())
    val products: StateFlow<List<ModelItem>> get() = _products

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _postSuccess = MutableStateFlow<Boolean?>(null)
    val postSuccess: StateFlow<Boolean?> get() = _postSuccess

    private val _postError = MutableStateFlow<String?>(null)
    val postError: StateFlow<String?> get() = _postError

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                delay(2000)
                val response = RetrofitClient.apiService.getAllProducts()
                _products.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun postProduct() {
        val product = ProductRequest(
            title = "New Product",
            price = 10,
            description = "A description",
            categoryId = 1,
            images = listOf("https://placeimg.com/640/480/any")
        )

        _isLoading.value = true
        RetrofitClient.apiService.postProduct(product).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        val newProduct = ModelItem(
                            id = it.id,
                            title = it.title,
                            price = it.price,
                            description = it.description,
                            images = it.images,
                            category = it.category,
                            creationAt = it.creationAt,
                            updatedAt = it.updatedAt
                        )
                        _products.value = _products.value + newProduct
                        _postSuccess.value = true
                        Log.d("ProductViewModel", "Product posted successfully: $newProduct")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("ProductViewModel", "Error: $errorMessage")
                    _postError.value = errorMessage
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("ProductViewModel", "Network Failure: ${t.message}")
                _postError.value = t.message
            }
        })
    }
}
