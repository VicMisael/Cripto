package com.example.cripto
import retrofit2.Retrofit
import retrofit2.converter.protobuf.ProtoConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory


object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ProtoConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(JaxbConverterFactory.create())
            .build()
    }
}
