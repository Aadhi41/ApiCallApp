package com.example.apicall.ui.Apicalls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicall.ui.Models.ModelItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<ModelItem>>(emptyList())
    val products: StateFlow<List<ModelItem>> get() = _products

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

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
}
