package com.vaibhav.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.vaibhav.local.models.User
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USER_SAVE_KEY = stringPreferencesKey("user")
    }

    suspend fun saveUserData(user: User) {
        val serializedUser = Gson().toJson(user)
        dataStore.edit {
            it[USER_SAVE_KEY] = serializedUser
        }
    }

    fun listenToUserData() = dataStore.data.map {
        val user = it[USER_SAVE_KEY]
        Gson().fromJson(user, User::class.java)
    }


}