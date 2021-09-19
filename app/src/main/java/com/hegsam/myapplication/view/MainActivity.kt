package com.hegsam.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hegsam.myapplication.adapter.RecyclerViewAdapter
import com.hegsam.myapplication.databinding.ActivityMainBinding
import com.hegsam.myapplication.model.CryptoModel
import com.hegsam.myapplication.service.CryptoAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://api.nomics.com/v1/"
    private lateinit var cryptoModels : ArrayList<CryptoModel>
    private lateinit var binding : ActivityMainBinding
    private lateinit var job : Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = service.getData()
            withContext(Dispatchers.Main)
            {
                if (response.isSuccessful)
                {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        binding.recyclerview.adapter = RecyclerViewAdapter(cryptoModels)

                    }
                }
            }
        }



    }
}