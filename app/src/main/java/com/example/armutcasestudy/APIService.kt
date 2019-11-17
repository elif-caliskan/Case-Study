package com.example.armutcasestudy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {

    @Headers(
        "x-rapidapi-host: omgvamp-hearthstone-v1.p.rapidapi.com",
        "x-rapidapi-key: 2308a9b553mshca3389fa9f9e669p1a2722jsnc30adbeaa1f7",
        "Content-Type: application/json")
    @GET("search/{name}")
    fun getCardList(@Path("name") k: String): Call<List<CardModel>>
}
