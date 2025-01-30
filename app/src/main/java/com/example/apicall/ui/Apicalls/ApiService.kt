package com.example.apicall.ui.Apicalls

import retrofit2.http.GET
import com.example.apicall.ui.Models.ModelItem
import com.example.apicall.ui.Models.ProductRequest
import com.example.apicall.ui.Models.ProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @GET("products/")
    suspend fun getAllProducts(): List<ModelItem>

    @POST("products/")
    fun postProduct(@Body product: ProductRequest): Call<ProductResponse>
}
