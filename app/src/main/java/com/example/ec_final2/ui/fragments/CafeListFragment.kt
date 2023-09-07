package com.example.ec_final2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ec_final2.R
import com.example.ec_final2.databinding.FragmentCafeListBinding
import com.example.ec_final2.ui.viewmodel.CafeListViewModel
import com.example.ec_final2.model.Cafe


class CafeListFragment : Fragment() {

    private lateinit var binding: FragmentCafeListBinding
    private lateinit var viewModel: CafeListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CafeListViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCafeListBinding.inflate(inflater ,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVCafeAdapter(listOf()) { cafe ->
           val destination = CafeListFragmentDirections.actionCafeListFragmentToCafeDetailFragment2(cafe)
            findNavController().navigate(destination)
        }

        binding.rvCafeList.adapter = adapter

        viewModel.cafes.observe(requireActivity()){
            adapter.cafes = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getCafesFromService()

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = viewModel.cafes.value?.filter { cafe ->
                    cafe.tipo.contains(newText ?: "", ignoreCase = true)
                } ?: listOf()
                adapter.cafes = filteredList
                adapter.notifyDataSetChanged()
                return true
            }
        })

    }

}