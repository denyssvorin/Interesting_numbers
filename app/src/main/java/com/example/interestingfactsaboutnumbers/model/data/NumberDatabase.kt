package com.example.interestingfactsaboutnumbers.model.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberData::class], version = 1)
abstract class NumberDatabase: RoomDatabase() {

    abstract fun numberDao(): NumberDao
}