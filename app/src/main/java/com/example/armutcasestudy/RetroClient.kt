package com.example.armutcasestudy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroClient private constructor() {
    private var retrofit: Retrofit? = null

    val apiService: APIService
        get() = retrofit!!.create(APIService::class.java)

    init {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    companion object {
        private val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/"
        private var mInstance: RetroClient? = null

        val instance: RetroClient
            @Synchronized get() {
                if (mInstance == null) {
                    mInstance = RetroClient()
                }
                return mInstance!!
            }
    }
}
