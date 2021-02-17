package com.example.anonymous_board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        writeNew.setOnClickListener(){
            var intent= Intent(this, com.example.anonymous_board.writeNew::class.java)
            // intent.putExtra("style",editSearch.text.toString())
            startActivity(intent)
        }

    }
}