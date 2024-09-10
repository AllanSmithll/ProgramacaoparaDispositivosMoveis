package com.example.retrofit_cep.service

import com.example.retrofit_cep.model.Endereco

import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoServiceIF {

    @GET("{cep}/json/")
    suspend fun getDetailsByCep(
        @Path("cep") cep: String,
    ): Endereco

}