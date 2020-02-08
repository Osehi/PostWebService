package com.polish.postwebservice.service

import com.polish.postwebservice.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostWebService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>


}