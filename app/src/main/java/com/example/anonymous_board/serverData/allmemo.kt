package com.example.anonymous_board.serverData

import com.example.anonymous_board.datacs.list
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface allmemo {// /web/androidtest.json
@GET("/staris/androidtest.do")
fun requestAllData(@Query("id") id:String="s", @Query("pw")pw:String="_pwd") : Call<list>

    /*
    @POST(~/{id})
    @Query("id") id: String ="s"
     */
}

