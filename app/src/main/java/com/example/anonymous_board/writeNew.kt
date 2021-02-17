package com.example.anonymous_board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write_new.*

class writeNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_new)


        save.setOnClickListener(){
            var Title:String=edittitle.text.toString();
            var conTent:String=content.text.toString()


            Toast.makeText(this,conTent,Toast.LENGTH_SHORT).show()
        }
        cancel.setOnClickListener(){
            var intent= Intent(this, Main::class.java)
            // intent.putExtra("style",editSearch.text.toString())
            startActivity(intent)
        }
    }
}