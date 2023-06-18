package com.laura_tfg.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.laura_tfg.myapplication.R
import com.laura_tfg.myapplication.adapter.JuegoAdapter
import com.laura_tfg.myapplication.data.Juego

class JuegoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JuegoAdapter

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        db = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.recyclerViewJuego)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = null

        val query = db.collection("juego").orderBy("nomJuego")
        val options = FirestoreRecyclerOptions.Builder<Juego>()
            .setQuery(query, Juego::class.java)
            .build()

        adapter = JuegoAdapter(options)
        recyclerView.adapter = adapter

        adapter.startListening()

        val btnEdad = findViewById<Button>(R.id.btnFiltro01)
        btnEdad.setOnClickListener { applyFiltro01() }

        val btnMaterial = findViewById<Button>(R.id.btnFiltro02)
        btnMaterial.setOnClickListener { applyFiltro02() }

        val btnUbicacion = findViewById<Button>(R.id.btnFiltro03)
        btnUbicacion.setOnClickListener { applyFiltro03() }

        val btnTiempo = findViewById<Button>(R.id.btnFiltro04)
        btnTiempo.setOnClickListener { applyFiltro04() }

        buscarJuego()
        detallesJuego()
    }

    private fun buscarJuego(){
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val db2 = FirebaseFirestore.getInstance().collection("juego")

                val query = db2.whereGreaterThanOrEqualTo("nomJuego", s.toString().trim())

                val newOptions = FirestoreRecyclerOptions.Builder<Juego>()
                    .setQuery(query, Juego::class.java)
                    .build()

                adapter.updateOptions(newOptions)
                adapter.notifyDataSetChanged()

            }
        })
    }

    private fun detallesJuego(){
        adapter.setOnItemClickListener( object : JuegoAdapter.OnItemClickListener {
            override fun onItemClick(documentId: String) {
                val intent = Intent(this@JuegoActivity, DetallesJuegoActivity::class.java)
                intent.putExtra("juegoId", documentId)
                startActivity(intent)
            }
        })
    }

    private fun applyFiltro01(){
        val db3 = FirebaseFirestore.getInstance().collection("juego")

        val query = db3.orderBy("rangoEdad")

        val newOptions3 = FirestoreRecyclerOptions.Builder<Juego>()
            .setQuery(query, Juego::class.java)
            .build()

        adapter.updateOptions(newOptions3)
        adapter.notifyDataSetChanged()
    }

    private fun applyFiltro02(){
        val db4 = FirebaseFirestore.getInstance().collection("juego")

        val query = db4.whereEqualTo("material", "Ninguno")

        val newOptions4 = FirestoreRecyclerOptions.Builder<Juego>()
            .setQuery(query, Juego::class.java)
            .build()

        adapter.updateOptions(newOptions4)
        adapter.notifyDataSetChanged()
    }

    private fun applyFiltro03(){
        val db5 = FirebaseFirestore.getInstance().collection("juego")

        val query = db5.whereIn("ubicacion", listOf( "Interior", "Ambas")).orderBy("nomJuego")

        val newOptions5 = FirestoreRecyclerOptions.Builder<Juego>()
            .setQuery(query, Juego::class.java)
            .build()

        adapter.updateOptions(newOptions5)
        adapter.notifyDataSetChanged()
    }

    private fun applyFiltro04(){
        val db6 = FirebaseFirestore.getInstance().collection("juego")

        val query = db6.whereEqualTo("tiempo", "10 - 15")

        val newOptions6 = FirestoreRecyclerOptions.Builder<Juego>()
            .setQuery(query, Juego::class.java)
            .build()

        adapter.updateOptions(newOptions6)
        adapter.notifyDataSetChanged()
    }
}