package com.vaibhav.remote.auth

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.vaibhav.remote.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class AuthState(
    val smsCode: String = "",
    val isComplete: Boolean = false,
    val userId: String = ""
)

class FirebaseAuthDataSource @Inject constructor(private val auth: FirebaseAuth) : AuthDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun signInUser(phoneNumber: String) = flow<Resource<AuthState>> {
        emit(Resource.Loading())
        sendOtp(phoneNumber).collect { status ->
            when (status) {
                is Resource.Error -> emit(Resource.Error(status.message))
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    emit(Resource.Success(AuthState(smsCode = status.data?.smsCode ?: "")))
                    val response = auth.signInWithCredential(status.data!!).await()
                    emit(
                        Resource.Success(
                            AuthState(
                                isComplete = true,
                                userId = response.user?.uid ?: ""
                            )
                        )
                    )
                }
            }
        }

    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    @ExperimentalCoroutinesApi
    override suspend fun registerUser(phoneNumber: String) = signInUser(phoneNumber)

    @ExperimentalCoroutinesApi
    override suspend fun sendOtp(phoneNumber: String) =
        callbackFlow {
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    offer(Resource.Success(p0))
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    offer(Resource.Error(p0.message.toString()))
                }
            }
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose()
        }


}