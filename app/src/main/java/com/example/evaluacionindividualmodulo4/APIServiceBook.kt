package com.example.evaluacionindividualmodulo4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServiceBook {
    @GET
    fun getBookByName(@Url url:String): Call<BooksResponse>
}