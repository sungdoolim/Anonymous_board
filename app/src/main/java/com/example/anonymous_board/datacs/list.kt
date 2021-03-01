package com.example.anonymous_board.datacs

data class list(
    val writer:String="",
    val title:String="",
    val content:String="",
    val datetime:String="",
    val id:Int=-1,
    var sendData: ArrayList<Any>?=null

)