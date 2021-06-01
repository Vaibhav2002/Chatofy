package com.vaibhav.repository

import com.vaibhav.local.dataStore.PreferencesDataStore
import com.vaibhav.remote.auth.AuthDataSource
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val preferencesDataStore: PreferencesDataStore
) {

    fun loginUser(phoneNumber: String) = flow {
        val loginState = authDataSource.signInUser(phoneNumber)
        //get user date from fireStore and save into SharedPreference
        emitAll(loginState)
    }

    suspend fun registerUser(phoneNumber: String) = flow {
        val loginState = authDataSource.signInUser(phoneNumber)
        //get user date from fireStore and save into SharedPreference
        emitAll(loginState)
    }

}