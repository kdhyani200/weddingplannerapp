package kd.dhyani.weddingplannerapp

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 🔹 Extension property to create a DataStore instance for the Context
val Context.dataStore by preferencesDataStore(name = "user_prefs")

// 🔹 SessionManager handles login session state using DataStore
class SessionManager(private val context: Context) {

    companion object {
        // 🔹 Key to store login state (true if user is logged in, false otherwise)
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // 🔹 Flow to observe login state changes
    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[IS_LOGGED_IN] ?: false // default to false if key not present
    }

    // 🔹 Suspend function to update login state
    suspend fun setLogin(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = value
        }
    }
}
