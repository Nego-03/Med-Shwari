package com.nego.medshwari.viewmodel


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nego.medshwari.data.PatientDao
import com.nego.medshwari.data.PatientDatabase
import com.nego.medshwari.model.Patient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.io.copyTo
import kotlin.io.use

class PatientViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val PatientDao = PatientDatabase.getDatabase(app).PatientDao()

    val allPatient: LiveData<List<Patient>> = PatientDao.getAllUsers()

    fun addPatient(name: String, email: Double, phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newPatient = Patient(
                name = name,
                email = email.toString(),
                phone = phone
            )

            PatientDao.insertPatient(newPatient)
        }
    }

    fun updatePatient(updatedPatient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            PatientDao.updatePatient(updatedPatient)
        }
    }

    fun deletePatient(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            PatientDao.deletePatient(patient)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}