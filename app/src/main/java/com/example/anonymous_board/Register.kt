package com.example.anonymous_board

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.anonymous_board.Main.Companion.baseUrl
import com.example.anonymous_board.datacs.list
import com.example.anonymous_board.datacs.userData
import com.example.anonymous_board.serverData.allList
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register.setOnClickListener(){
            var id=id.text.toString()
            var name=name.text.toString()
            var pw=pw.text.toString()
            register(id,pw,name)

        }
    }

    fun register(id:String,pw:String,name:String){
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(com.example.anonymous_board.serverData.register::class.java)
        retrofitService.requestAllData(id,pw,name).enqueue(object : Callback<userData> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<userData>, response: Response<userData>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is getallList")
                if (response.isSuccessful) {
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse getallList success")
                    val body = response.body()
                    val jsonObj =  JSONObject.wrap(body?.sendData)
                    println("jsonobj:${jsonObj}")
                    val jArray = jsonObj as JSONArray
                    println(jArray.length())
                    var ptmp : userData
                    var tmpar= arrayListOf<userData>()
                    for(i in 0..jArray.length()-1){
                        ptmp= userData(jArray.getJSONObject(i).getString("title"),jArray.getJSONObject(i).getString("writer"),jArray.getJSONObject(i).getString("content"),null)
                        tmpar.add(ptmp)
                    }
                    for(i in 0..tmpar.size-1){
                        println(tmpar.get(i).toString())
                    }

                    body?.let {
                        //text_text.text = body.toString response 잘 받아왔는지 확인.
                        println(body.toString())
                        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse    let")

                    }
                }
                else {
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse   else")
                }
            }
            override fun onFailure(call: Call<userData>, t: Throwable) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fail f2")
                Log.d("this is error",t.message.toString())
            }
        })
    }
}