package com.example.anonymous_board.serverData
import com.example.anonymous_board.datacs.list
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface delmemo {

    @GET("/staris/delmemo.do")
    fun requestAllData(@Query("id") id:Int=-1,@Query("writer") writer:String="null") : Call<list>

}

