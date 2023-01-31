package com.example.relationroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.relationroom.entity.Region

@Dao
interface RegionDao {

    @Insert
    fun addRegion(region: Region)

    @Query("select * from region")
    fun getAllRegions(): List<Region>
}