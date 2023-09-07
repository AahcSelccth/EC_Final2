package com.example.ec_final2.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ec_final2.databinding.ItemCafeBinding
import com.example.ec_final2.model.Cafe
import com.example.ec_final2.model.Cafe2

class RvFirestoreAdapter(var cafes: List<Cafe2>, val onCafeClick: (Cafe2) -> Unit) : RecyclerView.Adapter<FirestoreVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirestoreVH {
        val binding = ItemCafeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FirestoreVH(binding, onCafeClick )
    }

    override fun getItemCount(): Int = cafes.size

    override fun onBindViewHolder(holder: FirestoreVH, position: Int) {
        holder.bind(cafes[position])
    }
}

class FirestoreVH(private val binding: ItemCafeBinding, val onCafeClick: (Cafe2) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cafe2: Cafe2){
        binding.tvTipo.text = cafe2.tipo
        binding.tvCantidad.text = "${cafe2.cantidad} en stock"
        binding.tvPrecio.text = "S/." + cafe2.precio
        Glide.with(binding.root.context).load(cafe2.foto).into(binding.imgFoto)
        binding.root.setOnClickListener {
            onCafeClick(cafe2)
        }
    }
}