package com.example.jetpacksample.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow

@Entity
data class User(
    @PrimaryKey val id: Int,
    val email: String?,
    val avatar: String?,

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name") val firstName: String?,

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name") val lastName: String?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId:Int): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)


}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "user"
                ).build()
            }
        }
    }
}
