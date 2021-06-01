package com.vaibhav.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {

    private val Context.dataStore by preferencesDataStore(name = "ChatofyDataStore")

    @Provides
    fun Context.providesDataStore(): DataStore<Preferences> = dataStore
}