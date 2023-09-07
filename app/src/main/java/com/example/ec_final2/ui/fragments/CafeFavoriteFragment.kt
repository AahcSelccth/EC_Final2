package com.example.ec_final2.ui.fragments
/*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ec_final2.R
import com.example.ec_final2.databinding.FragmentCafeFavoriteBinding*/

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ec_final2.R
import com.example.ec_final2.databinding.FragmentCafeFavoriteBinding

class CafeFavoriteFragment : Fragment() {
/*
    private lateinit var binding : FragmentCafeFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCafeFavoriteBinding.inflate(inflater ,container,false)
        return binding.root
    }
*/

    private lateinit var binding: FragmentCafeFavoriteBinding
    private lateinit var viewModel: CafeFavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CafeFavoriteViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCafeFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVCafeAdapter(listOf()){cafe ->
           // val destination = CafeFavoriteFragmentDirections.actionNoteFavoriteFragmentToNoteDetailFragment(cafe)
            val destination = CafeFavoriteFragmentDirections.actionCafeFavoriteFragmentToCafeDetailFragment2(cafe)
            findNavController().navigate(destination)
        }
        binding.rvFavorites.adapter = adapter
        viewModel.favorites.observe(requireActivity()){
            adapter.cafes = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }

}
