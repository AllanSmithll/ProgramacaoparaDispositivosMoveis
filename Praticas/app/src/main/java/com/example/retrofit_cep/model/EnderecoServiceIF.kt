package com.example.retrofit_cep.model

import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoServiceIF {

    @GET("enderecos")
    suspend fun listar(): List<Endereco>

    @GET("{cep}/json/")
    suspend fun getEndereco(@Path("cep") cep: String): Endereco
}
