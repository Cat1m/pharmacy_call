package com.hungduy.pharmacycall.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences by lazy { createPrefs() }

    private fun createPrefs(): SharedPreferences {
        return try {
            buildPrefs()
        } catch (e: Exception) {
            Log.e("TokenStorage", "Failed to create EncryptedSharedPreferences, resetting", e)
            try {
                val file = File(context.filesDir.parent, "shared_prefs/${Constants.PREFS_FILE}.xml")
                if (file.exists()) file.delete()
                buildPrefs()
            } catch (e2: Exception) {
                Log.e("TokenStorage", "Retry failed, falling back to plain prefs", e2)
                context.getSharedPreferences(Constants.PREFS_FILE + "_plain", Context.MODE_PRIVATE)
            }
        }
    }

    private fun buildPrefs(): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            Constants.PREFS_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(token: String) {
        prefs.edit().putString(Constants.PREFS_KEY_TOKEN, token).apply()
    }

    fun getToken(): String? = prefs.getString(Constants.PREFS_KEY_TOKEN, null)

    fun clearToken() {
        prefs.edit().remove(Constants.PREFS_KEY_TOKEN).apply()
    }

    fun hasToken(): Boolean = getToken() != null
}
