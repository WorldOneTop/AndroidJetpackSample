package com.example.jetpacksample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    val email: String?,
    val avatar: String?,

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name") val firstName: String?,

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name") val lastName: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)