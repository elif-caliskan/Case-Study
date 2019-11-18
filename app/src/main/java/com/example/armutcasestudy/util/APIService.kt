package com.example.armutcasestudy.util

import com.example.armutcasestudy.fragment.CardModel
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
    fun cardListSearch(@Path("name") k: String): Call<MutableList<CardModel>>

    @Headers(
        "x-rapidapi-host: omgvamp-hearthstone-v1.p.rapidapi.com",
        "x-rapidapi-key: 2308a9b553mshca3389fa9f9e669p1a2722jsnc30adbeaa1f7",
        "Content-Type: application/json")
    @GET("classes/{class}")
    fun cardListFilter(@Path("class") k: String): Call<MutableList<CardModel>>
}
