package com.nego.medshwari.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String

)
