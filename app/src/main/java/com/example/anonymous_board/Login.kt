package com.example.anonymous_board

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.anonymous_board.datacs.userData
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {

    val baseUrl="http://192.168.234.119:8052"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        checklogin.setOnClickListener(){


                var userid=id.text.toString()
                var userpw=password.text.toString()
                print(userid+userpw)
            if (userid!="" &&userpw!="") {
                tryLogin(this, userid, userpw)
            }
            else{
                var intent=Intent(this,Main::class.java)
                startActivity(intent)
            }

        }
        cancel.setOnClickListener(){

        }
    }

    fun tryLogin(context: Context, id:String, pw:String){
        println("try")
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(com.example.anonymous_board.serverData.login::class.java)

        retrofitService.requestAllData(id,pw).enqueue(object : Callback<userData> {
            override fun onResponse(call: Call<userData>, response: Response<userData>) {
                if(response.isSuccessful){
                    val body = response.body()
                    println(body.toString())
                    if (body!!.userstate.equals("true")){
                        var intent= Intent(context, Main::class.java)
                        // intent.putExtra("style",editSearch.text.toString())
                        var pref=getSharedPreferences("session",0)
                        var edit=pref.edit()
                        edit.putString("sessionId",id)
                        //edit.putString("sessionName",body.username)
                        edit.apply()


                        startActivity(intent)
                    }else{

                    }

                }else{
                    println("why..?")
                }
            }
            override fun onFailure(call: Call<userData>, t: Throwable) {
                println(t.toString())
            }
        })
    }
}