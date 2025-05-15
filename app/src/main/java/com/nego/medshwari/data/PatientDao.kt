package com.nego.medshwari.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nego.medshwari.model.Patient

@Dao
interface PatientDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<Patient>>

    @Insert
    suspend fun insertPatient(patient: Patient)

    @Update
    suspend fun updatePatient(patient: Patient)

    @Delete
    suspend fun deletePatient(patient: Patient)
}