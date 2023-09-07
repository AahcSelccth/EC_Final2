package com.example.ec_final2.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ec_final2.databinding.ItemCafeBinding
import com.example.ec_final2.model.Cafe

class RVCafeAdapter(var cafes:List<Cafe>, val onCafeClick: (Cafe) -> Unit) : RecyclerView.Adapter<CafeVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeVH {
        val binding = ItemCafeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CafeVH(binding,onCafeClick)
    }

    override fun getItemCount(): Int = cafes.size

    override fun onBindViewHolder(holder: CafeVH, position: Int) {
        holder.bind(cafes[position])
    }

}

class CafeVH(private val binding: ItemCafeBinding, val onCafeClick: (Cafe) -> Unit): RecyclerView.ViewHolder(binding.root){

    fun bind(cafe: Cafe){
        binding.tvTipo.text = cafe.tipo
        binding.tvCantidad.text = "${cafe.cantidad} en stock"
        binding.tvPrecio.text = "S/." + cafe.precio
        Glide.with(binding.root.context).load(cafe.foto).into(binding.imgFoto)
        binding.root.setOnClickListener {
            onCafeClick(cafe)
        }
    }
}

