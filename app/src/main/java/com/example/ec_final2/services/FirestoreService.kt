package com.example.ec_final2.services

import com.example.ec_final2.model.Cafe
import com.example.ec_final2.model.Cafe2
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var firestore: FirebaseFirestore

class FirestoreService {



        init {
            firestore = FirebaseFirestore.getInstance()
         }


        fun getAll(onSuccess: (List<Cafe2>) -> Unit, onFailure: () -> Unit) {
            firestore.collection("cafe")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        onFailure()
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val cafes =
                            snapshot.documents.mapNotNull { it.toObject(Cafe2::class.java) }
                        onSuccess(cafes)
                    }
                }
        }


}