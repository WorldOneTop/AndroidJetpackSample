package com.example.jetpacksample.data

data class User(
    val id:Int,
    val name:String,
    val phoneNumber: String
){
    override fun toString() ="id : $id\nname : $name\nphoneNumber : $phoneNumber"
}