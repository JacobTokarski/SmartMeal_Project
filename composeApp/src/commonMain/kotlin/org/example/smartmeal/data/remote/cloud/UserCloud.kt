package org.example.smartmeal.data.remote.cloud

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.CollectionReference
import dev.gitlive.firebase.firestore.DocumentReference
import dev.gitlive.firebase.firestore.firestore
import io.github.aakira.napier.Napier
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object UserCloud {
    private val uid: String? get() = Firebase.auth.currentUser?.uid

    fun collection(name: String): CollectionReference? =
        uid?.let { Firebase.firestore.collection("users").document(it).collection(name) }

    fun document(collection: String, id: String): DocumentReference? =
        collection(collection)?.document(id)
}


object CloudScope {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    fun write(block: suspend () -> Unit) {
        scope.launch {
            try {
                block()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Napier.e ("Zapis do Firestore się nie powiódł",e )
            }
        }
    }
}