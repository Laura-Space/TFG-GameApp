package com.laura_tfg.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.laura_tfg.myapplication.R
import com.laura_tfg.myapplication.data.Juego

class DetallesJuegoActivity : AppCompatActivity() {

    private lateinit var juegoId: String
    private lateinit var textViewNomJuego : TextView
    private lateinit var textViewRangoEdad : TextView
    private lateinit var textViewUbicacion : TextView
    private lateinit var textViewMaterial : TextView
    private lateinit var textViewTipo : TextView
    private lateinit var textViewTiempo : TextView
    private lateinit var textViewCentro : TextView
    private lateinit var textViewObjetivos : TextView
    private lateinit var textViewDescripcion : TextView

    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_juego)

        juegoId = intent.getStringExtra("juegoId").orEmpty()
        textViewNomJuego = findViewById(R.id.textViewNomJuego)
        textViewRangoEdad = findViewById(R.id.textViewRangoEdad)
        textViewUbicacion = findViewById(R.id.textViewUbicacion)
        textViewMaterial = findViewById(R.id.textViewMaterial)
        textViewTipo = findViewById(R.id.textViewTipo)
        textViewTiempo = findViewById(R.id.textViewTiempo)
        textViewCentro = findViewById(R.id.textViewCentro)
        textViewObjetivos = findViewById(R.id.textViewObjetivos)
        textViewDescripcion = findViewById(R.id.textViewDescripcion)

        db = FirebaseFirestore.getInstance()

        getJuegoData()
    }

    private fun getJuegoData(){
        val juegoDocument = db.collection("juego").document(juegoId)

        juegoDocument.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()){
                val juego = documentSnapshot.toObject(Juego::class.java)
                juego?.let {
                    textViewNomJuego.text = it.nomJuego
                    textViewRangoEdad.text= it.rangoEdad
                    textViewUbicacion.text = it.ubicacion
                    textViewMaterial.text = it.material
                    textViewTipo.text = it.tipo
                    textViewTiempo.text = it.tiempo
                    textViewCentro.text = it.centro
                    textViewObjetivos.text = it.objetivo
                    textViewDescripcion.text = it.descripcion
                }
            }
        }.addOnFailureListener { exception ->
            // Manejar la excepción en caso de error
        }
    }
}