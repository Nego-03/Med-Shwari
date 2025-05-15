package com.nego.medshwari.repository

import android.content.Context
import com.nego.medshwari.data.PatientDatabase
import com.nego.medshwari.model.Patient

class PatientRepository(context: Context) {
    private val patientDao = PatientDatabase.getDatabase(context).PatientDao()

    suspend fun insertPatient(Patient: Patient) {
        patientDao.insertPatient(Patient)
    }

    fun getAllUsers() = patientDao.getAllUsers()

    suspend fun deletePatient(patient: Patient) = patientDao.deletePatient(patient)
}