package com.ledokol.thebestprojectever.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDao {

    @Insert
    fun insertProfile(profile: Profile)

    @Query("SELECT * FROM users")
    fun getProfile(): List<Profile>

    @Query("DELETE FROM users")
    fun clearProfile()

}