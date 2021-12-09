package com.example.retrofit_airways.repository

import com.example.retrofit_airways.model.ModelResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetroInstance {

    companion object{
        //const val baseUrl="https://api.instantwebtools.net"
       // const val baseUrl="https://mocki.io/"
       // const val baseUrl="https://mocki.io/"
        const val baseUrl="https://mocki.io/"
    }
   // @get:GET("v1/airlines")
  //  @get:GET("v1/ac12f1d1-c72f-4cce-b722-7e121991b79a")
    //@get:GET("v1/a5e687b5-9dd7-46fa-8872-31564a141a70")
    @get:GET("v1/8703cae8-f5f2-4682-9bd7-dfcc9eb2e861")
    val posts: Call<List<ModelResponse>>
}