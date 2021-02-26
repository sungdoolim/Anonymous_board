package com.example.anonymous_board

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymous_board.adapter.listAdapter
import com.example.anonymous_board.datacs.list
import com.example.anonymous_board.serverData.allList
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder
import java.net.URLEncoder

class Main : AppCompatActivity() {
    val baseUrl="http://192.168.234.119:8052"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pref=getSharedPreferences("session",0)
        var sessionId=pref.getString("sessionId","")
        if (sessionId.equals("")){

            Toast.makeText(this,"로그인을 해 주세요!",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"즐거운 하루 되세요 "+sessionId+"님!",Toast.LENGTH_SHORT).show()
        }


           selectList(this)
        writeNew.setOnClickListener(){
            var intent= Intent(this, com.example.anonymous_board.writeNew::class.java)
            // intent.putExtra("style",editSearch.text.toString())
            startActivity(intent)
        }

    }

    fun selectList(container: Context){

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

                    val body = response.body()

                    val jsonObj =  JSONObject.wrap(body?.sendData)

                   // println("jsonobj:${ String(jsonObj.toString().encodeToByteArray(),charset("UTF-8"))}")
                    val jArray = jsonObj as JSONArray
                    println(jArray.length())
                    var ptmp : list
                    var tmpar= arrayListOf<list>()
                    for(i in 0..jArray.length()-1){
                        ptmp= list(
                            jArray.getJSONObject(i).getString("title"),
                            jArray.getJSONObject(i).getString("writer"),
                            jArray.getJSONObject(i).getString("content")
                            ,
                            null)
                        tmpar.add(ptmp)
                    }
                    for(i in 0..tmpar.size-1){
                        println(tmpar.get(i).toString())
                    }
                    list.layoutManager=
                        LinearLayoutManager(container, LinearLayoutManager.VERTICAL,false)
                    list.setHasFixedSize(true)
                    list.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
                    list.adapter= listAdapter(container,tmpar)

                    body?.let {
                        //text_text.text = body.toString response 잘 받아왔는지 확인.
                        println(body.toString())
                    }
                }
                else {
                }
            }
            override fun onFailure(call: Call<list>, t: Throwable) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fail f2")
                Log.d("this is error",t.message.toString())
            }
        })
    }
}