package com.project.adersh.retrofitapikotlindemo.api

import com.project.adersh.foody.Root
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("random.php")
    suspend fun getFood():Response<Root>
}