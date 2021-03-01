package com.example.anonymous_board

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymous_board.adapter.listAdapter
import com.example.anonymous_board.datacs.list
import com.example.anonymous_board.serverData.allList
import com.example.anonymous_board.serverData.delmemo
import kotlinx.android.synthetic.main.activity_detail_content.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailContent : AppCompatActivity() {
    val baseUrl="http://192.168.11.4:8052"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_content)
        val pref=getSharedPreferences("listData",0)
        val session=getSharedPreferences("session",0)
        val edit=pref.edit()
        val Writer=pref.getString("writer","")
        val sessionId=session.getString("sessionId","").toString()
        val title=pref.getString("title","")
        val Content=pref.getString("content","")
        val idnum=pref.getInt("id",-1)
        if (Writer.equals(sessionId)){
            delete.visibility= View.VISIBLE
        }
        delete.setOnClickListener(){
        del(this,idnum,sessionId)
        }
        writer.setText("작성자 : "+Writer)
        content.setText("내용 \n"+Content)
        Title.setText("제목 : "+title)




    }
    fun del(container:Context,idnum:Int,sessionid:String){
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(delmemo::class.java)

        retrofitService.requestAllData(idnum,sessionid).enqueue(object : Callback<list> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<list>, response: Response<list>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is delete")
                if (response.isSuccessful) {

                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is success")
                }
                else {
                }
            }
            override fun onFailure(call: Call<list>, t: Throwable) {
                Log.d("this is error",t.message.toString())
            }
        })


    }
}