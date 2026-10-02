package org.example.smartmeal.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationRepository {

    private val _currentUser = MutableStateFlow<FirebaseUser?>(Firebase.auth.currentUser)

    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    sealed class AuthResult {
        object Success: AuthResult()
        data class Failure(val exception: Throwable): AuthResult()
    }

    suspend fun register(
        email: String,
        password: String,
        displayName: String
    ) : AuthResult {
        return try {
            val result = Firebase.auth.createUserWithEmailAndPassword(email, password)
            result.user?.updateProfile(displayName = displayName)
            _currentUser.value = Firebase.auth.currentUser
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Failure(e)
        }
    }

    suspend fun login(
        email: String,
        password: String,
    ) : AuthResult {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password)
            _currentUser.value = Firebase.auth.currentUser
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Failure(e)
        }
    }

    suspend fun logout() {
        Firebase.auth.signOut()
        _currentUser.value = null
    }
}