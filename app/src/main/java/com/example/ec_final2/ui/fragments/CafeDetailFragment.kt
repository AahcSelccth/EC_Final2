/*
package com.example.ec_final2.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ec_final2.databinding.FragmentCafeDetailBinding
import com.example.ec_final2.model.Cafe
import com.google.android.material.snackbar.Snackbar

class CafeDetailFragment : Fragment() {

    private lateinit var binding: FragmentCafeDetailBinding
    private val args: CafeDetailFragmentArgs by navArgs()
    private lateinit var cafe: Cafe
    private lateinit var viewModel: CafeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cafe = args.cafe
        viewModel = ViewModelProvider(requireActivity())[CafeDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCafeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    */
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtTipo.text = cafe.tipo
        binding.txtOrigen.text = cafe.origen
        binding.txtPrecio.text = cafe.precio.toString()
        binding.txtCantidad.text = cafe.cantidad.toString()
        Glide.with(binding.root.context).load(cafe.foto).into(binding.imgFoto)
        if (cafe.isFavorite) {
            binding.btnAddFavorite.visibility = View.GONE
        }
        binding.btnAddFavorite.setOnClickListener {
            cafe.isFavorite = true
            viewModel.addFavorites(cafe)
            Snackbar.make(binding.root,
                "Agregado a favoritos",
                Snackbar.LENGTH_SHORT).show()
        }
    }*/
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtTipo.text = cafe.tipo
        binding.txtOrigen.text = cafe.origen
        binding.txtPrecio.text = cafe.precio.toString()
        binding.txtCantidad.text = cafe.cantidad.toString()
        Glide.with(binding.root.context).load(cafe.foto).into(binding.imgFoto)

        if (cafe.isFavorite) {
            binding.btnAddFavorite.visibility = View.GONE
        }

        binding.btnAddFavorite.setOnClickListener {
            if (cafe.isFavorite) {
                Toast.makeText(requireContext(), "Este café ya ha sido agregado a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                cafe.isFavorite = true
                viewModel.addFavorites(cafe)
                Snackbar.make(binding.root,
                    "Agregado a favoritos",
                    Snackbar.LENGTH_SHORT).show()
                // También puedes ocultar el botón después de agregar a favoritos
                binding.btnAddFavorite.visibility = View.GONE
            }
        }
    }
}
*/


package com.example.ec_final2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ec_final2.databinding.FragmentCafeDetailBinding
import com.example.ec_final2.model.Cafe
import com.google.android.material.snackbar.Snackbar

class CafeDetailFragment : Fragment() {

    private lateinit var binding: FragmentCafeDetailBinding
    private val args: CafeDetailFragmentArgs by navArgs()
    private lateinit var cafe: Cafe
    private lateinit var viewModel: CafeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cafe = args.cafe
        viewModel = ViewModelProvider(requireActivity())[CafeDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCafeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtTipo.text = cafe.tipo
        binding.txtOrigen.text = cafe.origen
        binding.txtPrecio.text = cafe.precio.toString()
        binding.txtCantidad.text = cafe.cantidad.toString()
        Glide.with(binding.root.context).load(cafe.foto).into(binding.imgFoto)

        binding.btnAddFavorite.visibility = if (cafe.isFavorite) View.GONE else View.VISIBLE
        binding.btnEliminarFavorite.visibility =
            if (cafe.isFavorite) View.VISIBLE else View.GONE

        binding.btnAddFavorite.setOnClickListener {
            if (cafe.isFavorite) {
                Toast.makeText(
                    requireContext(),
                    "Este café ya ha sido agregado a favoritos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                cafe.isFavorite = true
                viewModel.addFavorites(cafe)
                Snackbar.make(
                    binding.root,
                    "Agregado a favoritos",
                    Snackbar.LENGTH_SHORT
                ).show()
                // Ocultar el botón "Añadir a Favoritos"
                binding.btnAddFavorite.visibility = View.GONE
                // Mostrar el botón "Eliminar de Favoritos"
                binding.btnEliminarFavorite.visibility = View.VISIBLE
            }
        }

        binding.btnEliminarFavorite.setOnClickListener {
            if (cafe.isFavorite) {
                cafe.isFavorite = false
                viewModel.removeFavorites(cafe)
                Snackbar.make(
                    binding.root,
                    "Quitado de favoritos",
                    Snackbar.LENGTH_SHORT
                ).show()
                // Ocultar el botón "Eliminar de Favoritos"
                binding.btnEliminarFavorite.visibility = View.GONE
                // Mostrar el botón "Añadir a Favoritos"
                binding.btnAddFavorite.visibility = View.VISIBLE
            } else {
                Toast.makeText(
                    requireContext(),
                    "Este café no está en tus favoritos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

