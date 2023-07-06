package com.example.interestingfactsaboutnumbers.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {

    @Query("SELECT * FROM numbers_table")
    fun getNumbers(): Flow<List<NumberData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumberData(data: NumberData)

}