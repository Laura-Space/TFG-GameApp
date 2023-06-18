package com.laura_tfg.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.laura_tfg.myapplication.R
import com.laura_tfg.myapplication.adapter.CentroAdapter
import com.laura_tfg.myapplication.data.Centro

class CentroActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CentroAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centro)

        db = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.recyclerViewCentro)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = null

        val query = db.collection("centro")
        val options = FirestoreRecyclerOptions.Builder<Centro>()
            .setQuery(query, Centro::class.java)
            .build()

        adapter = CentroAdapter(options)
        recyclerView.adapter = adapter

        adapter.startListening()
    }
}