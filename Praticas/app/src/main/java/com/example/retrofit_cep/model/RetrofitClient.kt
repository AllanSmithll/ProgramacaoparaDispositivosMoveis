package com.example.retrofit_cep.model.dados

import com.example.retrofit_cep.model.EnderecoServiceIF
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "localhost:3000/"

    val enderecoService: EnderecoServiceIF by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EnderecoServiceIF::class.java)
    }
}
