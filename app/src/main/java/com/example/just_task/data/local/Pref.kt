package com.example.just_task.data.local

import android.content.Context

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun onShowed(): Boolean {
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onBoardingShow() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun setNickName(firstName: String) {
        pref.edit().putString(NICK_NAME_KEY, firstName).apply()
    }

    fun getNickName(): String {
        return pref.getString(NICK_NAME_KEY, "").toString()
    }

    fun setPassword(secondName: String) {
        pref.edit().putString(PASSWORD, secondName).apply()
    }

    fun getPassword(): String {
        return pref.getString(PASSWORD, " ").toString()
    }

    fun setPicture(image: String) {
        pref.edit().putString(USER_PICTURE_KEY, image).apply()
    }

    fun getPicture(): String {
        return pref.getString(USER_PICTURE_KEY, "").toString()
    }


    companion object {
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
        const val NICK_NAME_KEY = "nick.name"
        const val PASSWORD = "password.key"
        const val USER_PICTURE_KEY = "user.picture"
    }
}