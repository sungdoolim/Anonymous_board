package com.example.anonymous_board.serverData


import com.example.anonymous_board.datacs.list
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface onememo {// /web/androidtest.json
@GET("/staris/andtest.do")
fun requestAllData(@Query("id") id:String="s",@Query("pw")pw:String="_pwd") : Call<list>
//여기서는 보내고 받아오는 거구나!!   id와 pw를 보내는거야!!!
    /*
    @POST(~/{id})
    @Query("id") id: String ="s"
     */
}



