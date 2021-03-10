package com.example.anonymous_board

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.anonymous_board.datacs.list
import com.example.anonymous_board.serverData.updateProfile
import kotlinx.android.synthetic.main.activity_my_page.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class myPage : AppCompatActivity() {
    val GALLERY=0
    var imageName=""
    var img_path = ""
    var real_photoUri: Uri? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        var pref=getSharedPreferences("session", 0)
        var edit=pref.edit()
        profileimg.setOnClickListener(){
            openAlbum()
        }
        submit.setOnClickListener(){
            if(real_photoUri!=null){
                uploadPhoto(real_photoUri!!, pref.getString("sessionId", "").toString())
//                val intent=Intent(this,couponList::class.java)
//                startActivity(intent)
            }else {
                Toast.makeText(this, "사진 없어 임마", Toast.LENGTH_SHORT).show()
            }
        }
        logout.setOnClickListener(){
            edit.putString("sessionId", "")
            edit.apply()
        }
    }
    fun uploadPhoto(photoUri: Uri, id: String) {
        var path = img_path
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + path)

        var file =  File(path)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val imgFile = MultipartBody.Part.createFormData("imgFile", file.name, requestFile)

        var retrofit = Retrofit.Builder()
            .baseUrl(Main.baseUrl)//http://192.168.56.1:8052
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(updateProfile::class.java)

        retrofitService.requestAllData(id, imgFile).enqueue(object :
            Callback<list> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<list>, response: Response<list>) {
                println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is delete")
                if (response.isSuccessful) {

                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onresponse this is success")
                } else {
                }
            }

            override fun onFailure(call: Call<list>, t: Throwable) {
                Log.d("this is error", t.message.toString())
            }
        })





    }


    fun getImagePathToUri(data: Uri?): String? {
        //사용자가 선택한 이미지의 정보를 받아옴
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = managedQuery(data, proj, null, null, null)
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()

        //이미지의 경로 값
        val imgPath: String = cursor.getString(column_index)
        Log.d("test", imgPath)

        //이미지의 이름 값
        val imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1)
        Toast.makeText(this, "이미지 이름 : $imgName", Toast.LENGTH_SHORT).show()
        this.imageName = imgName
        return imgPath
    } //end of getImagePathToUri()


    public fun openAlbum() {
        // 휴대폰 내에 앨범을 엽니다.
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        val pref=getSharedPreferences("session", 0)
        println("openalbum ${pref.getString("id", "")}")
        startActivityForResult(intent, GALLERY)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// 앨범이 선택되면 실행되는 듯 합니다.
        println("request code : ${requestCode}")
        println("result code : ${resultCode}")
        println("data : ${data.toString()}")

        if(requestCode==GALLERY){
            if(data!=null) {
                var photoUri = data?.data!!
                real_photoUri = photoUri
                profileimg.setImageURI(photoUri)
                 img_path = getImagePathToUri(data.getData()).toString();

            }else{
                return
            }
        }
    }
}