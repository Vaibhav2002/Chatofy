package com.vaibhav.remote.auth

import com.google.firebase.auth.PhoneAuthCredential
import com.vaibhav.remote.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AuthDataSource {

    suspend fun signInUser(phoneNumber: String): Flow<Resource<AuthState>>

    suspend fun registerUser(phoneNumber: String): Flow<Resource<AuthState>>

    suspend fun sendOtp(phoneNumber: String):Flow<Resource<PhoneAuthCredential>>
}