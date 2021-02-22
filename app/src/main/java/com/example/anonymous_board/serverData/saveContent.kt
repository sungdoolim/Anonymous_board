package com.example.anonymous_board.serverData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.anonymous_board.datacs.list
interface saveContent {
    @GET("/staris/saveContent.do")
    fun requestAllData(@Query("title") titles:String="titletest",@Query("content")contents:String="contenttest",@Query("writer")writers:String="writertest") : Call<list>
}


