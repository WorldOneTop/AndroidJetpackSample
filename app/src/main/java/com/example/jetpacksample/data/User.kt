package com.example.jetpacksample.data

data class User(
    val id:Int,
    val name:String,
    var phoneNumber: String
){
    override fun toString() ="id : $id\nname : $name\nphoneNumber : $phoneNumber"
}