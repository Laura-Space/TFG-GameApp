package com.laura_tfg.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.laura_tfg.myapplication.R
import com.laura_tfg.myapplication.data.Juego

class JuegoAdapter (options : FirestoreRecyclerOptions<Juego>) : FirestoreRecyclerAdapter<Juego, JuegoAdapter.JuegoViewHolder>(options){

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(documentId: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    class JuegoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val tvNombreJuego: TextView = itemView.findViewById(R.id.tvNombreJuego)
        val tvRangoEdad: TextView = itemView.findViewById(R.id.tvRangoEdad)
        val tvUbicacion: TextView = itemView.findViewById(R.id.tvUbicacion)
        val tvMaterial: TextView = itemView.findViewById(R.id.tvMaterial)
        val tvTipo: TextView = itemView.findViewById(R.id.tvTipo)
        val tvTiempo: TextView = itemView.findViewById(R.id.tvTiempo)
        val tvCentro: TextView = itemView.findViewById(R.id.tvCentro)
        val tvObjetivos: TextView = itemView.findViewById(R.id.tvObjetivos)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)

        fun bind(juego: Juego) {
            tvNombreJuego.text = juego.nomJuego
            tvRangoEdad.text = juego.rangoEdad
            tvUbicacion.text = juego.ubicacion
            tvMaterial.text = juego.material
            tvTipo.text = juego.tipo
            tvTiempo.text = juego.tiempo
            tvCentro.text = juego.centro
            tvObjetivos.text = juego.objetivo
            tvDescripcion.text = juego.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuegoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_juego, parent, false)

        return JuegoViewHolder(view)
    }

    override fun onBindViewHolder(holder: JuegoViewHolder, position: Int, model: Juego) {
        val documentSnapshot = snapshots.getSnapshot(position)
        val documentId = documentSnapshot.id

        holder.bind(model)

        holder.itemView.setOnClickListener {
            listener.onItemClick(documentId)
        }
    }
}