package com.example.interestingfactsaboutnumbers.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("numbers_table")

@Parcelize
data class NumberData (
    @PrimaryKey(autoGenerate = false) val number: Int = 0,
    val text: String = "fact"
): Parcelable

