package com.laura_tfg.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FirebaseFirestore
import com.laura_tfg.myapplication.R

class JuegoRandomActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego_random)

        db = FirebaseFirestore.getInstance()
        val juegosCollection = db.collection("juego")

        juegosCollection.get().addOnSuccessListener { querySnapshot ->
            val documents = querySnapshot.documents

            if(!documents.isEmpty()){
                val randomIndex = (0 until documents.size).random()

                val randomDocument = documents[randomIndex]

                val nombre = randomDocument.getString("nomJuego")
                val rango = randomDocument.getString("rangoEdad")
                val ubi = randomDocument.getString("ubicacion")
                val materiales = randomDocument.getString("material")
                val tipos = randomDocument.getString("tipo")
                val tiempos = randomDocument.getString("tiempo")
                val centros = randomDocument.getString("centro")
                val objetivos = randomDocument.getString("objetivo")
                val descripciones = randomDocument.getString("descripcion")

                val cardView : CardView = findViewById(R.id.cardViewJuegoRandom)
                val tViewNomJuego: TextView = cardView.findViewById(R.id.tViewNomJuego)
                val tViewRangoEdad: TextView = cardView.findViewById(R.id.tViewRangoEdad)
                val tViewUbicacion: TextView = cardView.findViewById(R.id.tViewUbicacion)
                val tViewMaterial: TextView = cardView.findViewById(R.id.tViewMaterial)
                val tViewTipo: TextView = cardView.findViewById(R.id.tViewTipo)
                val tViewTiempo: TextView = cardView.findViewById(R.id.tViewTiempo)
                val tViewCentro: TextView = cardView.findViewById(R.id.tViewCentro)
                val tViewObjetivos: TextView = cardView.findViewById(R.id.tViewObjetivos)
                val tViewDescripcion: TextView = cardView.findViewById(R.id.tViewDescripcion)


                tViewNomJuego.text = nombre
                tViewRangoEdad.text = rango
                tViewUbicacion.text = ubi
                tViewMaterial.text = materiales
                tViewTipo.text = tipos
                tViewTiempo.text = tiempos
                tViewCentro.text = centros
                tViewObjetivos.text = objetivos
                tViewDescripcion.text = descripciones
            }
        }
            .addOnFailureListener { exception ->
                // Manejo de errores
            }
    }
}