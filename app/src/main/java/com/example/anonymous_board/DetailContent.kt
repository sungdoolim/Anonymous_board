package com.example.anonymous_board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_content.*

class DetailContent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_content)
        val pref=getSharedPreferences("listData",0)
        val edit=pref.edit()
        val Writer=pref.getString("writer","")
        val title=pref.getString("title","")
        val Content=pref.getString("content","")

        writer.setText("작성자 : "+Writer)
        content.setText("내용 \n"+Content)
        Title.setText("제목 : "+title)




    }
}