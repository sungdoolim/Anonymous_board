
package com.example.anonymous_board.serverData
import com.example.anonymous_board.datacs.userData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface register {
    @GET("/staris/anonymousRegister.do")
    fun requestAllData(@Query("userId") id:String="s",
                       @Query("userPw")pw:String="_pwd",
                       @Query("userName")name:String="_name") : Call<userData>

}


