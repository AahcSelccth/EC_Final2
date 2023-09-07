package com.example.ec_final2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ec_final2.databinding.FragmentFirestoreBinding
import com.example.ec_final2.services.FirestoreService


class FirestoreFragment : Fragment() {
    private lateinit var binding: FragmentFirestoreBinding
    private lateinit var cafeService: FirestoreService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirestoreBinding.inflate(inflater, container, false)
        cafeService = FirestoreService()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RvFirestoreAdapter(emptyList()) {}
        binding.rvCafeList.adapter = adapter

        cafeService.getAll(onSuccess = { cafes ->
            adapter.cafes = cafes
            adapter.notifyDataSetChanged()
        }, onFailure = {

        })
    }
}
