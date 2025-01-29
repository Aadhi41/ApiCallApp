package com.example.apicall.ui.Apicalls

import retrofit2.http.GET
import com.example.apicall.ui.Models.ModelItem

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(): List<ModelItem>
}
