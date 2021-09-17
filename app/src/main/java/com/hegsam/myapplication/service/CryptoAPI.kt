package com.hegsam.myapplication.service

import com.hegsam.myapplication.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    @GET("prices?key=f775e68f620b7fb094df283bc632cc90a0d6224c")
    fun getData() : Call<List<CryptoModel>>
}