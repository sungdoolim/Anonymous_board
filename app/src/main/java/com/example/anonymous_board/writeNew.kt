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
    val baseUrl="http://192.168.234.119:8052"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_new)


        var intent= Intent(this, Login::class.java)
        startActivity(intent)



        save.setOnClickListener(){
            var Title:String=edittitle.text.toString();
            var conTent:String=content.text.toString()

            save_To_Server(Title,conTent)

            //f()
            callAllList()
            Toast.makeText(this,conTent,Toast.LENGTH_SHORT).show()
        }
        cancel.setOnClickListener(){
            f2()
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
                        ptmp= list(jArray.getJSONObject(i).getString("title"),jArray.getJSONObject(i).getString("writer"),jArray.getJSONObject(i).getString("content"),null)
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
    fun save_To_Server(title:String,content:String){


        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(saveContent::class.java)

        retrofitService.requestAllData(title,content,"newWriter").enqueue(object : Callback<list> {
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
    fun f2(){// 리스트를 받음

        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(allmemo::class.java)//내가만든 inter2 클래스 사용
        retrofitService.requestAllData().enqueue(object : Callback<list> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<list>, response: Response<list>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is f2")
                if (response.isSuccessful) {
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse success")

                    val body = response.body()

                    //val jsonObj = JSONTokener(body.toString())
                    val jsonObj =  JSONObject.wrap(body?.sendData)
                    println("jsonobj:${jsonObj}")
                    val jArray = jsonObj as JSONArray
                    println(jArray.length())
                    var ptmp :list
                    var tmpar= arrayListOf<list>()
                    for(i in 0..jArray.length()-1){
                        ptmp= list(jArray.getJSONObject(i).getString("f"),jArray.getJSONObject(i).getString("l"),"cont",null)
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
    fun f(){// 단일 클래스를 받음

        var baseUrl="http://192.168.234.119:8052"
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(onememo::class.java)// 내가만든 inter 클래스 사용
        retrofitService.requestAllData().enqueue(object : Callback<list> {
            override fun onResponse(call: Call<list>, response: Response<list>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is f")
                if (response.isSuccessful) {
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse success")

                    val body = response.body()
                    //val jsonObj = JSONTokener(body.toString())x`
                    //  val jsonObj =  JSONObject.quote(body.toString());
                    //   println("jsonobj:${jsonObj}")
                    //   val jArray = jsonObj[2]   as JSONArray?
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
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~fail f")
                Log.d("this is error",t.message.toString())
            }


        })
    }

}