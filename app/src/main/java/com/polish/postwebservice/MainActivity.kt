package com.polish.postwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.polish.postwebservice.model.Post
import com.polish.postwebservice.service.PostWebService
import com.polish.postwebservice.service.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // preparing the call
        val postService = ServiceBuilder.builderService(PostWebService::class.java)
        val requestCall = postService.getPosts()

        requestCall.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.isSuccessful){
                    var postItems = response.body()
                    for (item in postItems!!){
                        var content = ""
                        content += "ID: "+ item.id + "\n"
                        content += "User ID" + item.userId + "\n"
                        content += "Title: " + item.title + "\n"
                        content += "Text: " + item.body + "\n"

                        textResult.append(content)
                    }

//                    postItems?.let {
//
//                        textResult.text = postItems.toString()
//                    }
                } else {
                    Toast.makeText(this@MainActivity, "failed to retrieve postItems", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "failed to retrieve all items", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
