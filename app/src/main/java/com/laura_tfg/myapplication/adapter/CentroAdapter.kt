package com.laura_tfg.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.laura_tfg.myapplication.R
import com.laura_tfg.myapplication.data.Centro


class CentroAdapter (options : FirestoreRecyclerOptions<Centro>) : FirestoreRecyclerAdapter<Centro, CentroAdapter.CentroViewHolder>(options) {

    class CentroViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nombreTextView: TextView = itemView.findViewById(R.id.tvNombreCentro)
        private val centroTextView: TextView = itemView.findViewById(R.id.tvLugarCentro)
        private val contactoTextView : TextView = itemView.findViewById(R.id.tvContactCentro)

        fun bind(centro: Centro){
            nombreTextView.text = centro.nomCentro
            centroTextView.text = centro.lugarCentro
            contactoTextView.text = centro.contactCentro
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CentroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_centro, parent, false)

        return CentroViewHolder(view)
    }

    override fun onBindViewHolder(holder: CentroViewHolder, position: Int, model: Centro) {
        holder.bind(model)
    }

}