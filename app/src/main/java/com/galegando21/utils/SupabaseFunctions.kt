package com.galegando21.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.exception.AuthRestException
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://hddnmaaerppnfiiadiiw.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhkZG5tYWFlcnBwbmZpaWFkaWl3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjMxMDgyODcsImV4cCI6MjAzODY4NDI4N30.NjVcMV4Z1nMDYzfLVdGE8ElUeMOstz1QHw_3OBGebNs"
) {
    install(Postgrest)
    install(Auth)
}

suspend fun signUp(context: Context, email: String, password: String) {
    try {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        Toast.makeText(context,"Rexistrado correctamente", Toast.LENGTH_SHORT).show()
        saveToken(context)
    } catch (e: AuthRestException) {
        if (e.message == "Email rate limit exceeded") {
            Toast.makeText(context,"Error rexistrandote, límite de correos enviados superado", Toast.LENGTH_SHORT).show()
        } else if (e.message == "User already registered") {
            Toast.makeText(context,"Error rexistrandote, usuario xa rexistrado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Error rexistrandote, intentao máis tarde", Toast.LENGTH_SHORT).show()
        }
        Log.d("SupabaseFunctions", "Error signing up: ${e.message}")
        e.printStackTrace()
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error signing up: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun login(context: Context, email: String, password: String) {
    try {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
        Toast.makeText(context,"Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
        saveToken(context)
    } catch (e: BadRequestRestException) {
        Toast.makeText(context,"Error iniciando sesión, credenciais inválidas", Toast.LENGTH_SHORT).show()
        Log.d("SupabaseFunctions", "Error signing up: ${e.message}")
        e.printStackTrace()
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error signing in: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun signOut(context: Context) {
    try {
        supabase.auth.signOut()
        Toast.makeText(context,"Sesión pechada correctamente", Toast.LENGTH_SHORT).show()
        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.SUPABASE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(SharedPreferencesKeys.ACCESS_TOKEN)
        editor.apply()
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error signing out: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun isLoggedIn(context: Context): Boolean {
    val token = getToken(context)
    if (token != null) {
        supabase.auth.retrieveUser(token)
        supabase.auth.refreshCurrentSession()
        saveToken(context)
        return true
    } else {
        return false
    }
}

private fun saveToken(context: Context) {
    val token = supabase.auth.currentAccessTokenOrNull()
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.SUPABASE, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(SharedPreferencesKeys.ACCESS_TOKEN, token)
    editor.apply()
}

private fun getToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.SUPABASE, Context.MODE_PRIVATE)
    return sharedPreferences.getString(SharedPreferencesKeys.ACCESS_TOKEN, null)
}