package com.example.group6project

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object UserSessionManager {
    private const val USER_EMAIL_KEY = "user_email"

    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("SpiffyPref", Context.MODE_PRIVATE)
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun isLoggedIn(): Boolean {
        return getCurrentUser() != null
    }

    fun saveCurrentUserEmail() {
        val editor = sharedPreferences?.edit()
        editor?.putString(USER_EMAIL_KEY, getCurrentUser()?.email)
        editor?.apply()
    }

    fun getCurrentUserEmail(): String? {
        return sharedPreferences?.getString(USER_EMAIL_KEY, null)
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }
}
