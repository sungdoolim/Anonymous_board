package com.example.anonymous_board.serverData


import android.net.Uri
import com.example.anonymous_board.datacs.list
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface updateProfile {
    @Multipart
    @POST("/staris/uploadFile.do")
    fun requestAllData(@Query("id") id:String="",
                       @Part imgFile: MultipartBody.Part,
    ) : Call<list>

}
