package com.example.anonymous_board

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.anonymous_board.datacs.list
import com.example.anonymous_board.serverData.allList
import com.example.anonymous_board.serverData.allmemo
import com.example.anonymous_board.serverData.onememo
import com.example.anonymous_board.serverData.saveContent
import kotlinx.android.synthetic.main.activity_write_new.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class writeNew : AppCompatActivity() {
    val baseUrl="http://192.168.11.4:8052"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_new)


        var pref=getSharedPreferences("session",0)



        save.setOnClickListener(){
            var Title:String=edittitle.text.toString();
            var conTent:String=content.text.toString()
            var sessionId=pref.getString("sessionId","").toString()
            save_To_Server(Title,conTent,sessionId)

            //f()
            //callAllList()
            Toast.makeText(this,conTent,Toast.LENGTH_SHORT).show()
            var intent= Intent(this, Main::class.java)
            // intent.putExtra("style",editSearch.text.toString())
            startActivity(intent)
        }
        cancel.setOnClickListener(){
           // f2()
            var intent= Intent(this, Main::class.java)
            // intent.putExtra("style",editSearch.text.toString())
            startActivity(intent)
        }
    }
    fun callAllList(){
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(allList::class.java)
        retrofitService.requestAllData().enqueue(object : Callback<list> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<list>, response: Response<list>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is getallList")
                if (response.isSuccessful) {
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse getallList success")
                    val body = response.body()
                    val jsonObj =  JSONObject.wrap(body?.sendData)
                    println("jsonobj:${jsonObj}")
                    val jArray = jsonObj as JSONArray
                    println(jArray.length())
                    var ptmp :list
                    var tmpar= arrayListOf<list>()
                    for(i in 0..jArray.length()-1){
                        ptmp= list(
                            jArray.getJSONObject(i).getString("writer"),
                            jArray.getJSONObject(i).getString("title"),
                            jArray.getJSONObject(i).getString("content"),
                            jArray.getJSONObject(i).getString("datetime"),
                            jArray.getJSONObject(i).getInt("id"),
                            null)
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
            override fun onFailure(call: Call<list>, t: Throwable) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fail f2")
                Log.d("this is error",t.message.toString())
            }
        })
    }
    fun save_To_Server(title:String,content:String,sessionId:String){


        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(saveContent::class.java)

        retrofitService.requestAllData(title,content,sessionId).enqueue(object : Callback<list> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<list>, response: Response<list>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is savetest")
                if (response.isSuccessful) {
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse success")
                    val body = response.body()
                    val jsonObj =  JSONObject.wrap(body?.sendData)
                    println("jsonobj:${jsonObj}")
                    val jArray = jsonObj as JSONArray
                    println(jArray.length())
                    var ptmp :list
//                    var tmpar= arrayListOf<list>()
//                    for(i in 0..jArray.length()-1){
//                        ptmp= list(jArray.getJSONObject(i).getString("f"),jArray.getJSONObject(i).getString("l"),"cont",null)
//                        tmpar.add(ptmp)
//                    }
//                    for(i in 0..tmpar.size-1){
//                        println(tmpar.get(i).toString())
//                    }

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
            override fun onFailure(call: Call<list>, t: Throwable) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fail save")
                Log.d("this is error",t.message.toString())
            }


        })
    }


}