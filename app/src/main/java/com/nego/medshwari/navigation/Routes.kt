package com.nego.medshwari.navigation


const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_SPLASH = "splash"
const val ROUT_BOOKING = "booking"
const val ROUT_DASHBOARD = "user"
const val ROUT_NOTIFICCATION = "notificcation"
const val ROUT_CONTACT = "contact"
const val ROUT_PRESCRIPTION = "prescription"
const val ROUT_PATIENT = "patient"
const val ROUT_APPOINTMENT = "appointment"

//Authentication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"

//Products

const val ROUT_ADD_PATIENT = "add_user"
const val ROUT_PATIENT_LIST = "user_list"
const val ROUT_EDIT_PATIENT = "edit_user/{productId}"

// ✅ Helper function for navigation
fun editUserRoute(UserId: Int) = "edit_user/$UserId"
