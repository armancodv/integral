package com.armanco.integral.data.repository

import com.armanco.integral.data.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun get(userId: String): Task<DocumentSnapshot> {
        return firestore.collection(COLLECTION).document(userId)
            .get()
    }

    fun set(user: User): Task<Void>? {
        return user.userId?.let {
            firestore.collection(COLLECTION).document(it)
                .set(user.toMapRemoveNull(), SetOptions.merge())
        }
    }

    fun delete(userId: String): Task<Void> {
        return firestore.collection(COLLECTION).document(userId)
            .delete()
    }

    companion object {
        const val COLLECTION = "users"
    }
}