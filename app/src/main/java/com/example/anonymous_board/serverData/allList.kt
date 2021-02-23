package com.example.anonymous_board.serverData

import com.example.anonymous_board.datacs.list
import retrofit2.Call
import retrofit2.http.GET

interface allList {
    @GET("/staris/callMemoList.do")
    fun requestAllData() : Call<list>

}
    /*
    @POST(~/{id})
    @Query("id") id: String ="s"
     */

