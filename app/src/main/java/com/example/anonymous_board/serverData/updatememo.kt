package com.example.anonymous_board.serverData
import com.example.anonymous_board.datacs.list
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface updatememo {

    @GET("/staris/updatememo.do")
    fun requestAllData(@Query("id") id:Int=-1,
                       @Query("writer") writer:String="null",
                       @Query("title") titles:String="titletest",
                       @Query("content")contents:String="contenttest",
                       ) : Call<list>

}
